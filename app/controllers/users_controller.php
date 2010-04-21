<?
class UsersController extends AppController {
	
    var $name = 'Users';    
    var $components = array('Auth', 'Email');
	
	var $r;
	var $user;
	var $accounts;
	
	function beforeFilter() {
		
        $this->Auth->allow('index', 'register');
		$this->Auth->loginRedirect = array('controller' => 'users', 'action' => 'home');
		$this->Auth->fields = array('username' => 'email', 
									'password' => 'password');
		
		$this->user = $this->Auth->user();
		
		$this->set('user', $this->Auth->user());
		
		if (isset($this->user['User']['userid'])) {
			$this->accounts = $this->getAccounts($this->user['User']['userid']);
		}
		$this->set('accounts', $this->accounts);
		
		error_log($this->action.':'.print_r($_POST,1));
    }	
	
	function index()
	{
	  
	}
	
	function getAccounts($userid)
	{
		$hash = null;
		
		$sql = "SELECT * FROM accounts"
			. " WHERE userid = ".$userid
			. " ORDER BY ebayuserid";
		$res = $this->User->query($sql);
		foreach ($res as $i => $arr) {
			$a = $arr['accounts'];
			$hash[$a['accountid']] = $a;
		}
		
		return $hash;
	}
	
	function home()
	{
		$userid = $this->user['User']['userid'];
		
		$sql = "SELECT *"
			. " FROM items"
			. " JOIN accounts USING (accountid)"
			. " WHERE userid = ".$userid
			. " ORDER BY itemid DESC"
			. " LIMIT 100"; 
		$res = $this->User->query($sql);
		
		$this->set('items', $res);
		
	}
	
	function items()
	{
		$userid = $this->user['User']['userid'];
		
		/* check post parameters */
		$sql_filter = null;
		$sql_filter[] = "userid = ".$userid;
		
		if (!empty($_POST["accountid"]))
			$sql_filter[] = "accountid = '".mysql_real_escape_string($_POST["accountid"])."'";
		
		if (!empty($_POST["title"]))
			$sql_filter[] = "title LIKE '%".mysql_real_escape_string($_POST["title"])."%'";
		
		$limit  = empty($_POST["limit"])  ? 10 : $_POST["limit"];
		$offset = empty($_POST["offset"]) ?  0 : $_POST["offset"];
		
		/* create sql statement */
		$sql = "SELECT SQL_CALC_FOUND_ROWS"
			. " accounts.ebayuserid, items.*"
			. " FROM items"
			. " JOIN accounts USING (accountid)"
			. " WHERE ".implode(" AND ", $sql_filter);
		
		$sql .= " ORDER BY endtime DESC, itemid DESC";
		
		$sql .= " LIMIT ".$limit." OFFSET ".$offset;
		
		error_log($sql);
		$res = $this->User->query($sql);
		
		/* count total records */
		$res_cnt = $this->User->query("SELECT FOUND_ROWS() AS cnt");
		$cnt = $res_cnt[0][0]['cnt'];
		
		/* modify result records */
		foreach ($res as $idx => $row) {
			$a = $row['accounts'];
			$i = $row['items'];
			
			$item = null;
			$item['ebayuserid']  = $a['ebayuserid'];
			$item['itemid']      = $i['itemid'];
			$item['accountid']   = $i['accountid'];
			$item['title']       = $i['title'];
			$item['viewitemurl'] = $i['viewitemurl'];
			$item['listingstatus'] = $i['listingstatus'];
			
			if (isset($i['endtime'])) {
				if (date('Y-m-d', strtotime($i['endtime'])) == date('Y-m-d')) {
					$item['endtime'] = date('H:i', strtotime($i['endtime']));
				} else {
					$item['endtime'] = date('n月j日', strtotime($i['endtime']));
				}
			} else {
				$item['endtime'] = '-';
			}
			
			
			if ($i['listingstatus'] == 'Active') {
				$item['listingstatus_label'] = 'O';
			} else if ($i['listingstatus'] == 'Completed') {
				$item['listingstatus_label'] = '=';
			} else {
				$item['listingstatus_label'] = 'I';
			}
			
			$item['ebayitemid'] = isset($i['ebayitemid']) ? $i['ebayitemid'] : '-';
			$item['startprice'] = $i['startprice'];
			
			$items[] = $item;
		}
		
		$data['cnt'] = $cnt;
		$data['res'] = $items;
		
		print json_encode($data);
		
		exit;
	}
	
	function item($itemid)
	{
		$sql = "SELECT * FROM items WHERE itemid = ".$itemid;
		$res = $this->User->query($sql);
		
		print json_encode($res[0]['items']);
		
		exit;
		
		$this->set('item', $res[0]['items']);
	}
	
    function login() {
		
    }
	
    function logout() {
        $this->Auth->logout();
        $this->redirect('/');
    }
	
	function register() {
		
		if (!empty($this->data)) {
			
			$this->User->create();
			if ($this->User->save($this->data)) {
				
				// send signup email containing password to the user
				$this->Email->from = "a@a.a";
				$this->Email->to   = "fd3s.boost@gmail.com";
				$this->Email->subject = 'testmail';
				$this->Email->send('test message 1234');
				
				$this->Auth->login($this->data);
				
				$this->redirect("home");
			}		
			
		}
	}

	function accept()
	{
		if ($user = $this->Auth->user()) {
			$sql_insert = "INSERT INTO accounts"
				. " (userid, ebayuserid, ebaytoken, ebaytokenexp, created)"
				. " VALUES ("
				. " ".$user['User']['userid'].","
				. " '".mysql_real_escape_string($_GET["username"])."',"
				. " '".mysql_real_escape_string($_GET["ebaytkn"])."',"
				. " '".mysql_real_escape_string($_GET["tknexp"])."',"
				. " NOW())";
			$res = $this->User->query($sql_insert);
		}
	}
	
	function reject()
	{
		
	}
	
	function copy()
	{
		if (empty($_POST['item'])) return;
		
		// todo: list copy column names
		$sql_copy = "INSERT INTO items ("
			. " accountid, title, description, categoryid, "
			. " startprice"
			. " ) SELECT"
			. " accountid, title, description, categoryid, "
			. " startprice"
			. " FROM items"
			. " WHERE itemid IN (".implode(",", $_POST['item']).")"
			. " ORDER BY itemid";
		$res = $this->User->query($sql_copy);
		
		// todo: separate by userid
		$sql = "SELECT itemid, ebayuserid, title"
			 . " FROM items"
			 . " JOIN accounts USING (accountid)"
			 . " ORDER BY itemid DESC"
			 . " LIMIT ".count($_POST['item']);
		$res = $this->User->query($sql);
		
		print json_encode($res);
		
		exit;
	}
	
	function update()
	{
		if (empty($_POST['item'])) return;
						
		//	 . " DATE_FORMAT(starttime, '%m-%d %H:%i') AS starttime,"
		//	 . " DATE_FORMAT(endtime,   '%m-%d %H:%i') AS endtime,"
		$sql = "SELECT itemid, ebayuserid, ebayitemid,"
			. " starttime, endtime, title"
			. " FROM items"
			. " JOIN accounts USING (accountid)"
			. " WHERE itemid IN (".implode(",", $_POST['item']).")"
			. " ORDER BY itemid DESC"
			. " LIMIT ".count($_POST['item']);
		$res = $this->User->query($sql);
		
		error_log(print_r($res,1));
		print json_encode($res);
		
		exit;
	}
	
	function edit($itemid)
	{
	  if (!preg_match('/^[\d]+$/', $itemid)) return null;
	  
	  $arr = null;
	  foreach ($_POST as $k => $v) {
		$arr[] = $k." = '".mysql_real_escape_string($v)."'";
	  }
	  if (is_array($arr)) {
		$sql_update = "UPDATE items"
		  . " SET ".implode(', ', $arr)
		  . " WHERE itemid = ".$itemid;
		$res = $this->User->query($sql_update);
		
	  }
	  exit;
	}
	
	function delete()
	{
		if (empty($_POST['item'])) return;
		
		$sql = "DELETE FROM items"
			. " WHERE itemid IN (".implode(",", $_POST['item']).")";
		$res = $this->User->query($sql);
		
		exit;
	}
	
	function submit()
	{
		if (empty($_POST['item'])) return;
		
		$cmd = 'PATH=/usr/local/php-5.2.10/bin'
			. ' /var/www/dev.xboo.st/cake/console/cake'
			. ' -app /var/www/dev.xboo.st/app daemon'
			. ' additems '.implode(',', $_POST['item'])
			. ' > /dev/null &';
		system($cmd);
		error_log($cmd);
		
		exit;
	}
	
	function getitem($accountid, $ebayitemid)
	{
		$h = null;
		$h['RequesterCredentials']['eBayAuthToken'] = $this->accounts[$accountid]['ebaytoken'];
		$h['ItemID'] = $ebayitemid;
		
		$gio = $this->callapi('GetItem', $h);
		
		print_r($gio);
		
		exit;
	}
	
	function additems($itemids)
	{
		if (empty($itemids)) return;
		
		// read data from db
		$sql = "SELECT *"
			. " FROM items"
			. " JOIN accounts USING (accountid)"
			. " WHERE itemid IN (".implode(",", $itemids).")";
		$res = $this->User->query($sql);
		foreach ($res as $i => $arr) {
			
			// todo: just for debug
			$arr['items']['title'] = "(".$arr['items']['itemid'].")".$arr['items']['title'];
			
			// todo: cut string in another way
			$arr['items']['title'] = substr($arr['items']['title'], 0, 55);
			
			$accountid = $arr['items']['accountid'];
			$itemdata[$accountid]['accounts'] = $arr['accounts'];
			$itemdata[$accountid]['items'][]  = $arr['items'];
		}
		
		// todo: replace with callapi method
		$headers['X-EBAY-API-COMPATIBILITY-LEVEL'] = EBAY_COMPATLEVEL;
		$headers['X-EBAY-API-DEV-NAME']  = EBAY_DEVID;
		$headers['X-EBAY-API-APP-NAME']  = EBAY_APPID;
		$headers['X-EBAY-API-CERT-NAME'] = EBAY_CERTID;
		$headers['X-EBAY-API-CALL-NAME'] = 'AddItems';
		$headers['X-EBAY-API-SITEID']    = 0;
		
		$url = EBAY_SERVERURL;
		
		$pool = new HttpRequestPool();
		
		$seqidx = 0;
		foreach ($itemdata as $accountid => $hash) {
			
			$chunked = array_chunk($hash['items'], 5);
			foreach ($chunked as $chunkedidx => $items) {
				
				// build xml
				$h = null;
				$h['RequesterCredentials']['eBayAuthToken'] = $hash['accounts']['ebaytoken'];
				$h['Version']       = EBAY_COMPATLEVEL;
				$h['ErrorLanguage'] = 'en_US';
				$h['WarningLevel']  = 'High';
				
				foreach ($items as $i => $arr) {
					$h['AddItemRequestContainer'][$i]['MessageID'] = ($i+1);
					$h['AddItemRequestContainer'][$i]['Item'] = $this->xml_item($arr);
				}
				
				$xml_request = '<?xml version="1.0" encoding="utf-8" ?>'."\n"
					. '<AddItemsRequest xmlns="urn:ebay:apis:eBLBaseComponents">'."\n"
					. $this->xml($h, 1)
					. '</AddItemsRequest>'."\n";
				
				file_put_contents('/var/www/dev.xboo.st/app/tmp/apilogs/'
								  . date("mdHis").'.AddItems.request.'
								  . $hash['accounts']['ebayuserid'].'.'.$chunkedidx.'.xml',
								  $xml_request);
				
				$r = null;
				$r = new HttpRequest();
				$r->setHeaders($headers);
				$r->setMethod(HttpRequest::METH_POST);
				$r->setUrl($url);
				$r->setRawPostData($xml_request);
				$pool->attach($r);
				
				$seqmap[$seqidx]['accountid'] = $accountid;
				$seqmap[$seqidx]['chunkeidx'] = $chunkedidx;
				$seqidx++;
			}
		}
		
		/* execute api call */
		$pool->send();
		
		$ridx = 0;
		foreach ($pool as $r) {
			
			$accountid = $seqmap[$ridx]['accountid'];
			
			$xml_response = $r->getResponseBody();
			
			file_put_contents('/var/www/dev.xboo.st/app/tmp/apilogs/'
							  . date("mdHis").'.AddItems.response.'.$ridx.'.xml',
							  $xml_response);
			
			$xmlobj = simplexml_load_string($xml_response);
			foreach ($xmlobj->AddItemResponseContainer as $i => $obj) {
			  
				$idx = ($seqmap[$ridx]['chunkeidx'] * 5) + ($obj->CorrelationID - 1);
				
				if (isset($obj->ItemID)) {
					
					$h = null;
					$h['RequesterCredentials']['eBayAuthToken'] =
						$itemdata[$accountid]['accounts']['ebaytoken'];
					$h['ItemID'] = $obj->ItemID;
					$gio = $this->callapi('GetItem', $h);
					
					$j = null;
					$j['ebayitemid']  = $obj->ItemID;
					$j['starttime']   = $obj->StartTime;
					$j['endtime']     = $obj->EndTime;
					$j['viewitemurl'] = $gio->Item->ListingDetails->ViewItemURL;
					
					$sql_u = null;
					foreach ($j as $f => $v) {
						$sql_u[] = $f." = '".mysql_real_escape_string($v)."'";
					}			  
					
					$sql = "UPDATE items"
						. " SET ".implode(', ', $sql_u)
						. " WHERE itemid = ".$itemdata[$accountid]['items'][$idx]['itemid'];
					
					error_log($sql);
					$this->User->query($sql);
					
					$result = null;
					$result['ebayitemid'] = $obj->ItemID;
					$result['starttime']  = $obj->StartTime;
					$result['endtime']    = $obj->EndTime;
					
					$itemdata[$accountid]['items'][$idx]['result'] = $result;
					
				} else if (isset($obj->Errors)) {
					
					$itemdata[$accountid]['items'][$idx]['errors'] = $obj->Errors->LongMessage;
					
				} else {
					
					$itemdata[$accountid]['items'][$idx]['exception']  = '*** ?????? ***';
					
				}
				
			}
			
			$itemdata[$accountid]['response'] = $xmlobj;
			
			$ridx++;
		}
		
		return $itemdata;
	}
	
	function xml($arr, $depth=0)
	{
		$xml = "";
		foreach ($arr as $k => $v) {
			if (is_array($v)) {
				if (array_key_exists(0, $v)) {
					foreach ($v as $j => $tmp) {
						$xml .= str_repeat("\t", $depth)."<".$k.">\n";
						$xml .= $this->xml($tmp, ($depth+1));
						$xml .= str_repeat("\t", $depth)."</".$k.">\n";
					}
				} else {
					$xml .= str_repeat("\t", $depth)."<".$k.">\n";
					$xml .= $this->xml($v, ($depth+1));
					$xml .= str_repeat("\t", $depth)."</".$k.">\n";
				}
			} else {
				$xml .= str_repeat("\t", $depth)."<".$k.">".$v."</".$k.">"."\n";
			}
		}
		
		return $xml;
	}
	
	function xml_item($d)
	{
		$i['Title']       = $d['title'];
		$i['Description'] = $d['description'];
		$i['PrimaryCategory']['CategoryID'] = '279';
		$i['CategoryMappingAllowed'] = 'true';
		$i['Site']        = 'US';
		$i['Quantity']    = '1';
		$i['StartPrice']  = $d['startprice'];
		$i['ListingDuration'] = 'Days_7';
		$i['ListingType'] = 'Chinese';
		$i['DispatchTimeMax'] = '3';
		$i['ShippingDetails']['ShippingType'] = 'Flat';
		$i['ShippingDetails']['ShippingServiceOptions']['ShippingServicePriority'] = '1';
		$i['ShippingDetails']['ShippingServiceOptions']['ShippingService'] = 'USPSMedia';
		$i['ShippingDetails']['ShippingServiceOptions']['ShippingServiceCost'] = '2.50';
		$i['ReturnPolicy']['ReturnsAcceptedOption'] = 'ReturnsAccepted';
		$i['ReturnPolicy']['RefundOption'] = 'MoneyBack';
		$i['ReturnPolicy']['ReturnsWithinOption'] = 'Days_30';
		$i['ReturnPolicy']['Description'] = 'foobar';
		$i['ReturnPolicy']['ShippingCostPaidByOption'] = 'Buyer';
		$i['Country'] = 'US';
		$i['Currency'] = 'USD';
		$i['PostalCode'] = '95125';
		$i['PaymentMethods'] = 'PayPal';
		$i['PayPalEmailAddress'] = 'magicalbookseller@yahoo.com';
		$i['PictureDetails']['PictureURL'] =
		  'http://thumbs.ebaystatic.com/pict/41007087008080_0.jpg';
		
		return $i;
	}
	
	// todo: authorize login user or daemon process
	function getsellerlist($ebayuserid)
	{
		$sql = "SELECT * FROM accounts"
			. " WHERE ebayuserid = '".mysql_real_escape_string($ebayuserid)."'";
		$res = $this->User->query($sql);
		$account = $res[0]['accounts'];
		
		$h = null;
		$h['RequesterCredentials']['eBayAuthToken'] = $account['ebaytoken'];
		//$h['GranularityLevel'] = 'Fine'; // Coarse, Medium, Fine
		$h['DetailLevel'] = 'ItemReturnDescription';
		$h['StartTimeFrom'] = '2010-01-01 00:00:00';
		$h['StartTimeTo']   = date('Y-m-d H:i:s');
		$h['Pagination']['EntriesPerPage'] = 200;
		$h['Sort'] = 1;
		//$h['UserID'] = 'testuser_tlbbidder1';
		//$h['UserID'] = 'testuser_seamlessrick';
		
		$xmlobj = $this->callapi('GetSellerList', $h);
		
		foreach ($xmlobj->ItemArray->Item as $idx => $o) {
		  
			$i = null;
			$i['accountid']     = $account['accountid'];
			$i['ebayitemid']    = $o->ItemID;
			$i['starttime']     = $o->ListingDetails->StartTime;
			$i['endtime']       = $o->ListingDetails->EndTime;
			$i['viewitemurl']   = $o->ListingDetails->ViewItemURL;
			$i['title']         = $o->Title;
			$i['description']   = $o->Description;
			$i['startprice']    = $o->StartPrice;
			$i['galleryurl']    = $o->PictureDetails->GalleryURL;
			$i['categoryid']    = $o->PrimaryCategory->CategoryID;
			$i['categoryname']  = $o->PrimaryCategory->CategoryName;
			$i['listingstatus'] = $o->SellingStatus->ListingStatus;
			
			foreach ($i as $f => $v) {
				$i[$f] = "'".mysql_real_escape_string($v)."'";
			}			  
			
			/* SELECT */
			$sql_select = "SELECT itemid FROM items"
			  . " WHERE ebayitemid = ".$i['ebayitemid'];
			$res = $this->User->query($sql_select);
			if (!empty($res[0]['items']['itemid'])) {
			  
			  /* UPDATE */
			  $sql_updates = null;
			  foreach ($i as $f => $v) {
				$sql_updates[] = $f." = ".$v;
			  }
			  $sql_update = "UPDATE items"
				. " SET ".implode(",",$sql_updates)
				. " WHERE ebayitemid = ".$i['ebayitemid'];
			  $res = $this->User->query($sql_update);
			  
			} else {
			  
			  /* INSERT */
			  $sql_insert = "INSERT INTO items"
				. " (".implode(",", array_keys($i)).")"
				. " VALUES"
				. " (".implode(",", array_values($i)).")";
			  $res = $this->User->query($sql_insert);
			  
			}
		}
		
		exit;
	}
	
	function category($id=null)
	{
	  
	}
	
	function getcategories()
	{
		$sql = "SELECT * FROM accounts"
			. " WHERE ebayuserid = 'testuser_tokyo'";
		$res = $this->User->query($sql);
		$account = $res[0]['accounts'];
		
		$h = null;
		$h['RequesterCredentials']['eBayAuthToken'] = $account['ebaytoken'];
		$h['CategorySiteID'] = 0;
		$h['DetailLevel'] = 'ReturnAll';
		
		$xmlobj = $this->callapi("GetCategories", $h);
		
		foreach ($xmlobj->CategoryArray->Category as $i => $o) {
			$line[] = "("
					. $o->CategoryID.","
					. $o->CategoryLevel.","
					. "'".mysql_real_escape_string($o->CategoryName)."',"
					. $o->CategoryParentID
					. ")";
		}
		
		$sql = "INSERT INTO categories (id, level, name, parentid)"
		  . " VALUES ".implode(',', $line);
		$res = $this->User->query($sql);
		
		print '<pre>';
		print_r($line);
		print_r($xmlobj);		
		print '</pre>';
		exit;
	}
	
	function callapi($call, $xmldata)
	{
		/* prepare */
		$headers['X-EBAY-API-COMPATIBILITY-LEVEL'] = EBAY_COMPATLEVEL;
		$headers['X-EBAY-API-DEV-NAME']  = EBAY_DEVID;
		$headers['X-EBAY-API-APP-NAME']  = EBAY_APPID;
		$headers['X-EBAY-API-CERT-NAME'] = EBAY_CERTID;
		$headers['X-EBAY-API-CALL-NAME'] = $call;
		$headers['X-EBAY-API-SITEID']    = 0;
		
		$url = EBAY_SERVERURL;
		
		$this->r = new HttpRequest();
		$this->r->setHeaders($headers);
		
		/* request */
		$xml_request = '<?xml version="1.0" encoding="utf-8" ?>'."\n"
			. '<'.$call.'Request xmlns="urn:ebay:apis:eBLBaseComponents">'."\n"
			. $this->xml($xmldata, 1)
			. '</'.$call.'Request>'."\n";
		
		file_put_contents('/var/www/dev.xboo.st/app/tmp/apilogs/'
						  . date("mdHis").'.'.$call.'.request.xml',
						  $xml_request);

		/* response */
		$xml_response = $this->post($url, $xml_request);
		$xmlobj = simplexml_load_string($xml_response);
		
		file_put_contents('/var/www/dev.xboo.st/app/tmp/apilogs/'
						  . date("mdHis").'.'.$call.'.response.xml',
						  $xml_response);
		
		return $xmlobj;
	}
	
	function get($url)
	{
		$this->r->setMethod(HttpRequest::METH_GET);
		$this->r->setUrl($url);
		$this->r->send();
		
		$html = $this->r->getResponseBody();
		
		return $html;
	}
	
	function post($url, $postdata)
	{
		$this->r->setMethod(HttpRequest::METH_POST);
		$this->r->setUrl($url);
		$this->r->setRawPostData($postdata);
		$this->r->send();
		
		$html = $this->r->getResponseBody();
		
		return $html;
	}
}
?>
