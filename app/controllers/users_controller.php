<?
/**
 * todo:
 * - scrollbar appear when item detail expanded, width broken. [FIXED]
 * - use html5.
 * - unify json response to one code.
 * - i18n.
 * - scheduled additems by crontab.
 *
 */
class UsersController extends AppController {
	
    var $name = 'Users';    
    var $components = array('Auth', 'Email');
	
	var $user;
	var $accounts;
	
	function beforeFilter() {
		
		Configure::write('Config.language', 'jpn');
		error_log($this->action.' POST:'.print_r($_POST,1));
		
        $this->Auth->allow('index', 'register');
		$this->Auth->loginRedirect = array('controller' => 'users', 'action' => 'home');
		$this->Auth->fields = array('username' => 'email',  'password' => 'password');
		$this->user = $this->Auth->user();
		$this->set('user', $this->Auth->user());
		
		if (isset($this->user['User']['userid'])) {
			$this->accounts = $this->getAccounts($this->user['User']['userid']);
		}
		$this->set('accounts', $this->accounts);
    }	
	
	function index()
	{
	  // todo: avoid /users/home request 
		if (isset($this->user['User']['userid'])) {
			$this->set('site', $this->sitedetails());
			$this->render('home');
		}
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
		
	}
	
	
	/**
	 * get summary data of items.
	 */
	function items()
	{
		$userid = $this->user['User']['userid'];
		
		/* check post parameters */
		$sql_filter = null;
		$sql_filter[] = "userid = ".$userid;
		
		// todo: avoid sql injection
		if (!empty($_POST["id"])) {
		  $sql_filter[] = "id IN (".implode(",", $_POST['id']).")";
		}
		
		if (!empty($_POST["ItemID"]))
			$sql_filter[] = "ItemID = '".mysql_real_escape_string($_POST["ItemID"])."'";
		
		if (!empty($_POST["accountid"]))
			$sql_filter[] = "accountid = '".mysql_real_escape_string($_POST["accountid"])."'";

		if (!empty($_POST["Title"]))
			$sql_filter[] = "Title LIKE '%".mysql_real_escape_string($_POST["Title"])."%'";
		
		$sql_selling['Scheduled'] = "ListingDetails_StartTime > NOW()";
		
		$sql_selling['Active']    = "ItemID IS NOT NULL"
			. " AND ListingDetails_EndTime > Now()";
		
		$sql_selling['Sold']      = "ItemID IS NOT NULL"
			. " AND SellingStatus_QuantitySold > 0";
		
		$sql_selling['Unsold']    = "ListingDetails_EndTime < Now()"
			. " AND SellingStatus_QuantitySold = 0";
		
		$sql_selling['Other']     = "ItemID IS NULL";
		
		if (!empty($_POST['selling']) && $_POST['selling'] != 'All') {
		  $sql_filter[] = $sql_selling[$_POST['selling']];
		}
		
		$limit  = empty($_POST["limit"])  ? 10 : $_POST["limit"];
		$offset = empty($_POST["offset"]) ?  0 : $_POST["offset"];
		
		/* create sql statement */
		// todo: timezone convert.
		$sql = "SELECT SQL_CALC_FOUND_ROWS"
			. " accounts.ebayuserid,"
			. " items.id,"
			. " items.ItemID,"
			. " CONVERT_TZ(items.ListingDetails_EndTime, 'GMT', 'Japan') AS ListingDetails_EndTime,"
			. " items.ListingDetails_ViewItemURL,"
			. " items.Title,"
			. " items.PictureDetails_PictureURL,"
			. " items.StartPrice,"
			. " items.Site,"
			. " items.SellingStatus_ListingStatus,"
			. " items.Errors_LongMessage,"
			. " items.status"
			. " FROM items"
			. " JOIN accounts USING (accountid)"
			. " WHERE ".implode(" AND ", $sql_filter);
		
		$sql .= " ORDER BY ListingDetails_EndTime ASC, id DESC";
		
		$sql .= " LIMIT ".$limit." OFFSET ".$offset;
		
		$res = $this->User->query($sql);
		//error_log($sql);
		
		/* count total records */
		$res_cnt = $this->User->query("SELECT FOUND_ROWS() AS cnt");
		$cnt = $res_cnt[0][0]['cnt'];
		
		/* modify result records */
		foreach ($res as $idx => $row) {
			
			$row['items']['ListingDetails_EndTime'] = $row[0]['ListingDetails_EndTime'];
			
			$id = $row['items']['id'];
			$item = $row['items'];
			$item['ebayuserid'] = $row['accounts']['ebayuserid'];
			
			/* ListingDetails_EndTime */
			if (isset($item['ListingDetails_EndTime'])) {
				if (date('Y-m-d', strtotime($item['ListingDetails_EndTime'])) == date('Y-m-d')) {
					$item['ListingDetails_EndTime'] =
						date('H:i', strtotime($item['ListingDetails_EndTime']));
				} else {
					$item['ListingDetails_EndTime'] =
						date('M j', strtotime($item['ListingDetails_EndTime']));
				}
			} else {
				$item['ListingDetails_EndTime'] = '-';
			}
			
			/* StartPrice */
			$item['StartPrice'] = number_format($item['StartPrice']);
			
			$item['Errors_LongMessage'] = explode("\n", $item['Errors_LongMessage']);
			
			/* add to rows */
			$items[] = $item;
		}
		
		$data['cnt'] = $cnt;
		$data['res'] = $items;
		
		echo json_encode($data);
		//error_log(print_r($data,1));
		
		exit;
	}
	
	
	/**
	 * get detail data of one item.
	 */
	function item()
	{
		// todo: check userid and accountid
		$sql = "SELECT * FROM items WHERE id = ".$_POST['id'];
		$res = $this->User->query($sql);
		$data = $res[0]['items'];
		
		// 
		$data['PictureDetails_PictureURL'] = explode("\n", $data['PictureDetails_PictureURL']);
		
		// todo: avoid infinite loop
		if ($data['PrimaryCategory_CategoryID'] > 0) {
			$data['categorypath'] =
				$this->categorypath($data['Site'], $data['PrimaryCategory_CategoryID']);
			$data['categoryfeatures']  =
				$this->categoryfeatures($data['Site'], $data['PrimaryCategory_CategoryID']);
		}
		
		$data['other']['site'] = $this->sitedetails();
		
		//error_log(print_r($data,1));
		//error_log(json_encode($data));
		echo json_encode($data);
		
		exit;
	}
	
	
	/**
	 * upload image file into web server.
	 * todo: various error check
	 */
	function upload()
	{
		if (isset($_FILES) && is_array($_FILES)) {
			foreach ($_FILES as $fname => $arr) {
				if ($arr['error'] != 0) continue;
				
				preg_match('/_([\d]+)_([\d]+)$/', $fname, $matches);
				$id  = $matches[1];
				$num = $matches[2];
				
				preg_match('/([^\.]+)$/', $arr['name'], $matches);
				$ext = $matches[1];
				$savename = $fname.'_'.date('YmdHis').'.'.$ext;
				
				move_uploaded_file($arr['tmp_name'], ROOT.'/app/webroot/itemimg/'.$savename);
				
				$sql = "SELECT PictureDetails_PictureURL FROM items WHERE id = ".$id;
				$res = $this->User->query($sql);
				$arrurl = explode("\n", $res[0]['items']['PictureDetails_PictureURL']);
				$arrurl[$num-1] = 'http://localhost/itemimg/'.$savename;
				$sql = "UPDATE items"
					. " SET PictureDetails_PictureURL"
					. " = '".mysql_real_escape_string(implode("\n", $arrurl))."'"
					. " WHERE id = ".$id;
				$res = $this->User->query($sql);
				
				error_log($fname);
				error_log(print_r($arr,1));
				
				$this->set('id', $id);
				$this->set('arrurl', $arrurl);
			}
		}
		
		$this->layout = null;
		
	}
	
	
	function description($id)
	{
		//$id = $_POST['itemid'];
		$sql = "SELECT description FROM items WHERE id = ".$id;
		$res = $this->User->query($sql);
		$html = $res[0]['items']['description'];
		
		echo $html;
		
		exit;
	}
	

	/**
	 * login
	 */
    function login() {
		
    }


	/**
	 * logout
	 */
    function logout() {
        $this->Auth->logout();
        $this->redirect('/');
    }
	

	/**
	 * register new user.
	 */
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
	
	
	/**
	 * callback from ebay oauth flow.
	 */
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
	
	
	/**
	 * copy items
	 */
	function copy()
	{
		if (empty($_POST['id'])) return;
		
		$cols = $this->getitemcols();
		unset($cols['id']);
		unset($cols['ItemID']);
		unset($cols['created']);
		unset($cols['updated']);
		unset($cols['ListingDetails_StartTime']);
		unset($cols['ListingDetails_EndTime']);
		$colnames = array_keys($cols);
		
		// todo: list copy column names
		$sql_copy = "INSERT INTO items (".implode(',', $colnames).", created, updated)"
			. " SELECT ".implode(',', $colnames).", NOW(), NOW() FROM items"
			. " WHERE id IN (".implode(",", $_POST['id']).")"
			. " ORDER BY id";
		$res = $this->User->query($sql_copy);
		
		exit;
	}
	
	
	function update()
	{
		if (empty($_POST['id'])) return;
		
		$id = $_POST['id'];
		
		$sqlcol = null;
		foreach ($_POST as $k => $v) {
			if (is_array($v)) {
				$sqlcol[] = $k." = '".mysql_real_escape_string(implode("\n", $v))."'";
			} else {
				$sqlcol[] = $k." = '".mysql_real_escape_string($v)."'";
			}
		}
		
		$sql_update = "UPDATE items SET ".implode(", ", $sqlcol)." WHERE id = ".$id;
		error_log($sql_update);
		$res = $this->User->query($sql_update);
		
		$_POST = null;
		$_POST['id'] = $id;
		
		$this->item();
		
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
	
	
	/**
	 * delete items.
	 */
	function delete()
	{
		if (empty($_POST['item'])) return;
		
		$sql = "DELETE FROM items"
			. " WHERE itemid IN (".implode(",", $_POST['item']).")";
		$res = $this->User->query($sql);
		
		exit;
	}
	
	
	
	function getitem($accountid, $ebayitemid)
	{
		$h = null;
		$h['RequesterCredentials']['eBayAuthToken'] = $this->accounts[$accountid]['ebaytoken'];
		$h['ItemID'] = $ebayitemid;
		
		$gio = $this->callapi('GetItem', $h);
		
		/*
		foreach ($gio->Item->children() as $o) {
			echo $o->getName()."[".$o."]<br>";
			//print_r($o);
		}
		*/
		
		$this->xml2arr($gio->Item, $arr, '');
		
		echo '<pre>';
		print_r($arr);
		print_r($gio->Item);
		echo '</pre>';
		
		exit;
	}
	
	function arr2xml(&$arr, $depth, $val)
	{
		if (count($depth) > 1) {
			$here = array_shift($depth);
			$res[$here] = $this->arr2xml($arr, $depth, $val);
			return $res;
		} else {
			$here = $depth[0];
			if (is_array($val)) {
				$res[$here] = $val;
			} else {
				$res[$here] = htmlspecialchars($val);
			}
			return $res;
		}
	}
	
	function xml2arr($xml, &$arr, $path)
	{
		foreach ($xml->children() as $child) {
			if (count($child->children())) {
				if ($path) {
					$this->xml2arr($child, $arr, $path.".".$child->getName());
				} else {
					$this->xml2arr($child, $arr, $child->getName());
				}
			} else {
				if ($path) {
					$newpath = $path.".".$child->getName();
				} else {
					$newpath = $child->getName();
				}
				
				if (isset($arr[$newpath])) {
					if (!is_array($arr[$newpath])) {
						$org = $arr[$newpath];
						$arr[$newpath] = array($org);
					}
					$arr[$newpath][] = $child.'';
				} else {
					$arr[$newpath] = $child.'';
					
					foreach ($child->attributes() as $attr) {
						$arr[$newpath.'@'.$attr->getName()] = $attr.'';
					}
				}
			}
		}
	}
	
	function enditems($ids=null)
	{
		if (empty($ids) && isset($_POST['id'])) {
			
			// If called from browser, kick background process and exit
			$cmd = 'PATH=/usr/local/php/bin'
				. ' '.ROOT.'/cake/console/cake'
				. ' -app '.ROOT.'/app daemon'
				. ' enditems '.implode(',', $_POST['id'])
				. ' > /dev/null &';
			system($cmd);
			exit;
			
		} else if (empty($ids)) {
			return;
		}
		
		
		
	}
	
	function relistitem($id)
	{
	  $ids[] = $id;
	  
		$sites = $this->sitedetails();
		
		// read item data from database
		// todo: check user account id
		$sql = "SELECT *"
			. " FROM items"
			. " JOIN accounts USING (accountid)"
			. " WHERE id IN (".implode(",", $ids).")";
		$res = $this->User->query($sql);
	  
		foreach ($res as $i => $arr) {

		  $accounts = $arr['accounts'];
		  $items    = $arr['items'];
		  
		  $h = null;
		  $h['RequesterCredentials']['eBayAuthToken'] = $accounts['ebaytoken'];
		  $h['Item'] = $this->xml_item($items);
		  
		  $rsp = $this->callapi('RelistItem', $h);
		  
		}
		
	}
	
	function addscheduleditems()
	{
		$sql = "SELECT id, schedule"
			. " FROM items"
			. " WHERE schedule < NOW()"
			. " AND schedule != '0000-00-00 00:00:00'"
			. " AND ItemID IS NULL"
			. " ORDER BY schedule";
		$res = $this->User->query($sql);
		$rows = null;
		foreach ($res as $i => $row) {
			$id = $row['items']['id'];
			$rows[$id] = $row;
			error_log($id.' '.$row['items']['schedule']);
		}
		
		if (is_array($rows)) {
			$cmd = 'PATH=/usr/local/php/bin '.ROOT.'/cake/console/cake'
				. ' -app '.ROOT.'/app daemon additems '.implode(',', array_keys($rows))
				. ' > /dev/null &';
			system($cmd);
		}
		
		return;
	}
	
	function additems($ids=null)
	{
		if (empty($ids)) {
			// If called from browser, kick background process.
			if (isset($_POST['id'])) {
				$cmd = 'PATH=/usr/local/php/bin '.ROOT.'/cake/console/cake'
					. ' -app '.ROOT.'/app daemon additems '.implode(',', $_POST['id'])
					. ' > /dev/null &';
				system($cmd);
			}
			exit;
		}
		
		$sites = $this->sitedetails();
		
		// read item data from database
		// todo: check user account id
		// todo: avoid duplicate listing submit
		$sql = "UPDATE items SET status = 10"
			. " WHERE id IN (".implode(",", $ids).")"
			. " AND ItemID IS NULL";
		$res = $this->User->query($sql);
		
		$sql = "SELECT * FROM items"
			. " JOIN accounts USING (accountid)"
			. " WHERE id IN (".implode(",", $ids).")"
			. " AND ItemID IS NULL";
		$res = $this->User->query($sql);
		foreach ($res as $i => $arr) {
			$accountid = $arr['items']['accountid'];
			$site      = $arr['items']['Site'];
			$accounts[$accountid] = $arr['accounts'];
			$itemdata[$accountid][$site][] = $arr['items'];
		}
		
		$pool = new HttpRequestPool();
		$seqidx = 0;
		foreach ($itemdata as $accountid => $sitehash) {
			foreach ($sitehash as $site => $hash) {
				$chunked = array_chunk($hash, 5);
				foreach ($chunked as $chunkedidx => $items) {
					
					$seqidx++;
					$h = null;
					$h['RequesterCredentials']['eBayAuthToken'] =
						$accounts[$accountid]['ebaytoken'];
					
					foreach ($items as $i => $arr) {
						$arr['ApplicationData'] = 'id:'.$arr['id']; // SKU
						$h['AddItemRequestContainer'][$i]['MessageID'] = ($i+1);
						$h['AddItemRequestContainer'][$i]['Item'] = $this->xml_item($arr);
						
						$seqmap[$seqidx][($i+1)] = $arr['id'];
					}
					
					$r = $this->getHttpRequest('AddItems', $h, $site);
					$pool->attach($r);
				}
			}
		}
		
		try {
			$pool->send();
		} catch (HttpRequestPoolException $ex) {
			error_log("pool->send() error\n".$ex->getMessage());
		}
		
		$ridx = 0;
		foreach ($pool as $r) {
			
			$ridx++;
			
			/* HTTP error */
			if ($r->getResponseCode() != 200) {
				error_log('Error[ridx:'.$ridx.']'
						  . '['.$r->getResponseCode().']['.$r->getResponseStatus().']');
				$sql = "UPDATE items SET status = 0,"
					. " Errors_LongMessage = 'Network error. Please try again later.'"
					. " WHERE id IN (".implode(",", $seqmap[$ridx]).")";
				$this->User->query($sql);
				
				continue;
			}
			
			$xml_response = $r->getResponseBody();
			
			$resfile = ROOT.'/app/tmp/apilogs/'
				. (9999999999-date("mdHis")).'.AddIs.res.'.substr('0'.$ridx, -2).'.xml';
			file_put_contents($resfile, $xml_response);
			chmod($resfile, 0777);
			
			$xmlobj = simplexml_load_string($xml_response);
			foreach ($xmlobj->AddItemResponseContainer as $i => $obj) {
				
				$id = $seqmap[$ridx][$obj->CorrelationID.''];
				
				$j = null;
				$j['status'] = 0;
				$j['schedule'] = '0000-00-00 00:00:00';
				
				if (isset($obj->ItemID)) {
					
					$j['ItemID'] = $obj->ItemID;
					$j['SellingStatus_ListingStatus'] = 'Active'; // manually?
					$j['ListingDetails_StartTime'] = $obj->StartTime;
					$j['ListingDetails_EndTime']   = $obj->EndTime;
					
				} else if (isset($obj->Errors)) {
					
					error_log(print_r($obj,1));
					
					$arrerrshort = null;
					$arrerrlong  = null;
					foreach ($obj->Errors as $ei => $eo) {
						$arrerrshort[] = $eo->ShortMessage;
						$arrerrlong[]  = $eo->LongMessage;
					}
					
					$j['Errors_ShortMessage'] = implode("\n", $arrerrshort);
					$j['Errors_LongMessage']  = implode("\n", $arrerrlong);
				}
				
				$sql_u = null;
				foreach ($j as $f => $v) {
					$sql_u[] = $f." = '".mysql_real_escape_string($v)."'";
				}			  
				
				// todo: check userid/accountid
				$sql = "UPDATE items SET ".implode(', ', $sql_u)." WHERE id = ".$id;
				$this->User->query($sql);
				//error_log($sql);
			}
			
		}
		
		return;
	}
	
	
	/**
	 * convert php array to xml string.
	 */
	function xml($arr, $depth=0)
	{
		$xml = "";
		foreach ($arr as $k => $v) {
			if (is_array($v)) {
				if (array_key_exists(0, $v)) {
					if (is_array($v[0])) {
						foreach ($v as $j => $tmp) {
							$xml .= str_repeat("\t", $depth)."<".$k.">\n";
							$xml .= $this->xml($tmp, ($depth+1));
							$xml .= str_repeat("\t", $depth)."</".$k.">\n";
						}
					} else {
						foreach ($v as $j => $tmp) {
							$xml .= str_repeat("\t", $depth)."<".$k.">".$tmp."</".$k.">"."\n";
						}
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
	
	function xml_item($i)
	{
		$xml = array();
		foreach ($i as $col => $val) {
			// todo: delete ItemID from caller function AddItems
			// todo: manage necessary column names in other way.
			if (preg_match('/(^[a-z]|ListingDetails|CategoryName|Error)/', $col)
				|| preg_match('/@/', $col)
				|| $val == '') {
				continue;
			}
			
			if ($col == 'PaymentMethods' || $col == 'PictureDetails_PictureURL') {
				$val = preg_replace("/^\n/", "", $val);
				$val = explode("\n", $val);
			} else if ($col == 'ScheduleTime') {
				$val = str_replace(' ', 'T', $val).'Z';
			}
			
			$depth = explode('_', $col);
			$xml = array_merge_recursive($xml, $this->arr2xml($arr, $depth, $val));
		}
		
		return $xml;
	}
	
	
	/**
	 * get column name of items table.
	 */
	function getitemcols()
	{
		$sql = "DESC items;";
		$res = $this->User->query($sql);
		foreach ($res as $i => $row) {
			if (preg_match('/@/', $row['COLUMNS']['Field'])) {
				$f['`'.$row['COLUMNS']['Field'].'`'] = $row;
			} else {
				$f[$row['COLUMNS']['Field']] = $row;
			}
		}
		
		return $f;
	}
	
	function getebaydetails()
	{
		$h = null;
		$h['RequesterCredentials']['eBayAuthToken'] = $this->accounts[8]['ebaytoken'];
		$h['DetailName'] = 'ShippingServiceDetails';
		$xmlobj = $this->callapi('GeteBayDetails', $h);

		foreach ($xmlobj->ShippingServiceDetails as $idx => $o) {
			$arr = null;
			$this->xml2arr($o, $arr, '');
			pr($arr);
		}
		
		exit;
	}
	
	function getcategoryfeatures($site, $categoryid=null)
	{
		if (true) {
			$h = null;
			$h['RequesterCredentials']['eBayAuthToken'] = $this->accounts[8]['ebaytoken'];
			$h['DetailLevel'] = 'ReturnAll';
			//$h['ViewAllNodes'] = 'true';
			//$h['AllFeaturesForCategory'] = 'true';
			//$h['FeatureID'] = 'ListingDuration';
			if ($categoryid) {
				$h['CategoryID'] = $categoryid;
			}
			$xmlobj = $this->callapi('GetCategoryFeatures', $h);
			
		} else {
			$tmp = file_get_contents(ROOT.'/app/tmp/apilogs/CategoryFeatures.xml');
			$xmlobj = simplexml_load_string($tmp);
		}
		
		//$this->xml2arr($xmlobj, $arr, '');
		
		$ns = $xmlobj->getDocNamespaces();
		$xmlobj->registerXPathNamespace('ns', $ns['']);
		
		echo '<pre>';
		print_r($xmlobj->xpath("/ns:GetCategoryFeaturesResponse"
							   . "/ns:Category[ns:CategoryID=".$categoryid."]"));
		$fd = $xmlobj->xpath("/ns:GetCategoryFeaturesResponse"
							 . "/ns:FeatureDefinitions"
							 . "/ns:ListingDurations"
							 . "/ns:ListingDuration");
		echo '<pre>'.print_r($fd,1).'</pre>';
		foreach ($fd as $i => $o) {
			
			$o->registerXPathNamespace('ns', $ns['']);
			
			$attr = $o->attributes();
			$setid = $attr['durationSetID'];
			
			$dur = $o->children($ns['']);
			
			$a = null;
			foreach ($dur as $j => $v) {
				$a[] = $v.'';
			}
			
			echo $setid.' : '.print_r($a,1).'<br>';
		}
		
		$ld = $xmlobj->xpath("/ns:GetCategoryFeaturesResponse"
							 . "/ns:SiteDefaults"
							 . "/ns:ListingDuration");
		echo '<pre>'.print_r($ld,1).'</pre>';
		
		foreach ($ld as $i => $o) {
			$attr = $o->attributes();
			$t = $attr['type'];
			echo $t.' : '.$o.'<br>';
		}
		
		//print_r($xmlobj->xpath("/ns:GetCategoryFeaturesResponse/ns:FeatureDefinitions"));
		//print_r($xmlobj);
		echo '</pre>';
		//echo '<pre>'.print_r($arr).'</pre>';
		exit;
	}
	
	
	// todo: authorize login user or daemon process
	function getsellerlist($ebayuserid, $userid=null)
	{
		$sql = "SELECT * FROM accounts"
			. " WHERE ebayuserid = '".mysql_real_escape_string($ebayuserid)."'";
		$res = $this->User->query($sql);
		$account = $res[0]['accounts'];
		
		// todo: I can specify the span by EndTime
		$h = null;
		$h['RequesterCredentials']['eBayAuthToken'] = $account['ebaytoken'];
		//$h['GranularityLevel'] = 'Fine'; // Coarse, Medium, Fine
		$h['DetailLevel'] = 'ReturnAll'; // ItemReturnDescription, ReturnAll
		$h['StartTimeFrom'] = '2010-04-01 00:00:00';
		$h['StartTimeTo'] =
			date('Y-m-d H:i:s', strtotime('+90day', strtotime($h['StartTimeFrom'])));
		$h['Pagination']['EntriesPerPage'] = 50;
		$h['Pagination']['PageNumber'] = 1;
		$h['Sort'] = 1;
		if ($userid) {
			$h['UserID'] = $userid;
		}
		
		while (true) {
			
			$xmlobj = $this->callapi('GetSellerList', $h);
			$this->getsellerlist_import($xmlobj, $account);
			
			if ($xmlobj->HasMoreItems == 'true') {
				$h['Pagination']['PageNumber']++;
			} else {
				break;
			}
		}
		
		exit;
	}
	
	
	/**
	 * 
	 */
	function getsellerlist_import($xmlobj, $account)
	{
		$colnames = $this->getitemcols();
		
		foreach ($xmlobj->ItemArray->Item as $idx => $o) {
			
			$arr = null;
			$i = null;
			$this->xml2arr($o, $arr, '');
			
			foreach ($arr as $c => $v) {
				$c = str_replace('.','_',$c);
				if (isset($colnames[$c])) {
					
					//if ($c == 'TimeLeft') $v = $this->duration2str($v);
					
					if ($c == 'PictureDetails.PictureURL' && is_array($v)) {
						$v = implode("\n", $v);
					} else if ($c == 'PaymentMethods' && is_array($v)) {
						$v = implode("\n", $v);
					}
					
					if (preg_match('/@/', $c)) $c = '`'.$c.'`';
					$i[$c] = "'".mysql_real_escape_string($v)."'";
				}
			}
			//echo error_log(print_r($arr,1));
			
			/* SELECT */
			// todo: catch INSERT/UPDATE query result.
			// todo: unique constraint of ItemID
			$res = $this->User->query("SELECT id FROM items WHERE ItemID = ".$i['ItemID']);
			if (!empty($res[0]['items']['id'])) {
				
				// todo: check accountid
				/* UPDATE */
				$sql_updates = null;
				foreach ($i as $f => $v) {
					$sql_updates[] = $f." = ".$v;
				}
				$sql_update = "UPDATE items"
					. " SET ".implode(",",$sql_updates)
					. " WHERE ItemID = ".$i['ItemID'];
				$res = $this->User->query($sql_update);
				//error_log($sql_update);
				
			} else {
				
				$i['created']   = "NOW()";
				$i['accountid'] = $account['accountid'];
				
				/* INSERT */
				$sql_insert = "INSERT INTO items"
					. " (".implode(",", array_keys($i)).")"
					. " VALUES"
					. " (".implode(",", array_values($i)).")";
				$res = $this->User->query($sql_insert);
				//error_log($sql_insert);
			}
		}
		
		return;
	}
	
	
	/**
	 * get hierarchical path data of specific category and its parents.
	 */
	function categorypath($site, $categoryid)
	{
		$table = "categories_".strtolower($site);
		$data = null;
		
		while (true) {
			
			/* myself */
			$res = $this->User->query
				("SELECT * FROM ".$table." WHERE CategoryID = ".$categoryid);
			if (empty($res[0][$table])) break;
			$row = $res[0][$table];
			$data['level'][$row['CategoryLevel']] = $row['CategoryID'];
			$parentid = $row['CategoryParentID'];
			
			/* siblings */
			$sibs = null;
			if ($row['CategoryLevel'] == 1) {
				$sql2 = "SELECT * FROM ".$table
					. " WHERE CategoryLevel = ".$row['CategoryLevel'];
			} else {
				$sql2 = "SELECT * FROM ".$table
					. " WHERE CategoryParentID = ".$row['CategoryParentID']
					. " AND CategoryLevel = ".$row['CategoryLevel'];
			}
			$res2 = $this->User->query($sql2);
			foreach ($res2 as $i => $row2) {
				// todo: show > for indicate whether have children.
				$sibs[] = $row2[$table];
			}
			$data['nodes'][$row['CategoryLevel']] = $sibs;
			
			/* next loop for upper depth */
			if ($row['CategoryLevel'] == 1) break;
			$categoryid = $row['CategoryParentID'];
		}
		if (is_array($data['level'])) {
			ksort($data['level']);
		}
		
		return $data;
	}
	
	
	/**
	 * 
	 */
	function category()
	{
	  $site = $_POST['site'];
	  $categoryid = null;
	  if (isset($_POST['categoryid'])) {
		$categoryid = $_POST['categoryid'];
	  }
		
		$data = array();
		
		/* child categories */
		$table = "categories_".strtolower($site);
		$sql = "SELECT * FROM ".$table;
		if (empty($categoryid)) {
		  $sql .= " WHERE CategoryLevel = 1";
		} else {
		  $sql .= " WHERE CategoryParentID = ".$categoryid
			. " AND CategoryID != ".$categoryid;
		}
		$res = $this->User->query($sql);
		if (count($res) > 0) {
			foreach ($res as $i => $row) {
				$rows[] = $row[$table];
			}
			$data['categories'] = $rows;
		}
		
		$data['categoryfeatures'] = $this->categoryfeatures($site, $categoryid);
		
		/* response */
		//error_log(print_r($data,1));
		error_log(json_encode($data));
		echo json_encode($data);
		exit;
	}
	
	
	/**
	 *
	 * todo: inherit features from its all parents.
	 */
	function categoryfeatures($site, $categoryid=null)
	{
		/* load xml */
		$xml = file_get_contents(ROOT.'/app/tmp/apilogs/CategoryFeatures.xml');
		$xmlobj = simplexml_load_string($xml);
		$ns = $xmlobj->getDocNamespaces();
		$xmlobj->registerXPathNamespace('ns', $ns['']);
		
		/* DurationSet */
		$xmlobj_ld = $xmlobj->xpath('/ns:GetCategoryFeaturesResponse'
									. '/ns:FeatureDefinitions'
									. '/ns:ListingDurations'
									. '/ns:ListingDuration');
		foreach ($xmlobj_ld as $i => $o) {
			
			$o->registerXPathNamespace('ns', $ns['']);
			$attr = $o->attributes();
			$setid = $attr['durationSetID'].'';
			$dur = $o->children($ns['']);
			
			$a = null;
			//$a['Days_1'] = '1 Day';
			foreach ($dur as $j => $v) {
				$v = $v.''; // todo: cast string
				if (preg_match('/^Days_([\d]+)$/', $v, $matches)) {
					$a[$v] = $matches[1].' Days';
				} else if ($v == 'GTC') {
					$a[$v] = "Good 'Til Cancelled";
				}
			}
			
			$durationset[$setid] = $a;
		}
		//error_log(print_r($durationset,1));
		
		
		/* SiteDefaults */
		$sdns = '/ns:GetCategoryFeaturesResponse/ns:SiteDefaults';
		
		$xmlobj_sd = $xmlobj->xpath($sdns.'/ns:ListingDuration');
		foreach ($xmlobj_sd as $i => $o) {
			$attr = $o->attributes();
			$type = $attr['type'].'';
			$typedefault[$type] = $o.'';
		}
		
		$xmlobj_pm = $xmlobj->xpath($sdns.'/ns:PaymentMethod');
		foreach ($xmlobj_pm as $i => $o) {
			if ($o.'' == 'CustomCode') continue;
			$arrpm[] = $o.'';
		}
		
		/* overwrite by child nodes */
		$path = null;
		if ($categoryid) {
		  $path = $this->categorypath($site, $categoryid);
		}
		if (is_array($path['level'])) {
		  foreach ($path['level'] as $level => $cid) {
			
			$cns = "/ns:GetCategoryFeaturesResponse/ns:Category[ns:CategoryID=".$cid."]";
			
			$ld = $xmlobj->xpath($cns."/ns:ListingDuration");
			if ($ld) {
				foreach ($ld as $i => $o) {
					$attr = $o->attributes();
					$type = $attr['type'].'';
					$typedefault[$type] = $o.'';
				}
			}
			
			$pm = $xmlobj->xpath($cns."/ns:PaymentMethod");
			if ($pm) {
				$tmppm = null;
				foreach ($pm as $i => $o) {
					if ($o.'' == 'CustomCode') continue;
					$tmppm[] = $o.'';
				}
				$arrpm = $tmppm;
			}
		  }
		}
		
		/* result  */
		foreach ($typedefault as $type => $setid) {
			$data['ListingDuration'][$type] = $durationset[$setid];
		}
		// it's tricky!
		$data['ListingDuration']['Chinese'] =
			array('Days_1' => '1 Day') + $data['ListingDuration']['Chinese'];
		
		$data['PaymentMethod'] = $arrpm;
		//error_log(print_r($data,1));
		
		return $data;
	}
	
	function sitedetails()
	{
		/* load xml */
		$xml = file_get_contents(ROOT.'/app/tmp/apilogs/SiteDetails.xml');
		$xmlobj = simplexml_load_string($xml);
		$ns = $xmlobj->getDocNamespaces();
		$xmlobj->registerXPathNamespace('ns', $ns['']);
		
		foreach ($xmlobj->SiteDetails as $o) {
			$site = $o->Site.'';
			$siteid = $o->SiteID.'';
			$data[$site] = $siteid;
		}
		
		return $data;
	}
	
	/**
	 * 
	 */
	function getcategories()
	{
		$sql = "SELECT * FROM accounts WHERE ebayuserid = 'testuser_tokyo'";
		$res = $this->User->query($sql);
		$account = $res[0]['accounts'];
		
		$sites = $this->sitedetails();
		foreach ($sites as $sitename => $siteid) {
			
			if ($siteid != 77) continue;
			
			if (false) {
				$h = null;
				$h['RequesterCredentials']['eBayAuthToken'] = $account['ebaytoken'];
				$h['CategorySiteID'] = $siteid;
				$h['DetailLevel'] = 'ReturnAll';
				
				$xmlobj = $this->callapi("GetCategories", $h, $sitename);
			} else {
				
				$tmp = file_get_contents(ROOT.'/app/tmp/apilogs/GetCategories.'.$sitename.'.xml');
				$xmlobj = simplexml_load_string($tmp);
				
				
				$line = null;
				foreach ($xmlobj->CategoryArray->Category as $i => $o) {
					$line[] = "("
						. $o->CategoryID.","
						. $o->CategoryLevel.","
						. $o->CategoryParentID.","
						. "'".mysql_real_escape_string($o->CategoryName)."',"
						. ($o->LeafCategory ? 1 : 0)
						. ")";
				}
				
				$res = $this->User->query("DELETE FROM categories_".strtolower($sitename));
				$sql = "INSERT INTO categories_".strtolower($sitename)." ("
					. " CategoryID,"
					. " CategoryLevel,"
					. " CategoryParentID,"
					. " CategoryName,"
					. " LeafCategory)"
					. " VALUES ".implode(',', $line);
				$res = $this->User->query($sql);
				
			}
			
		}
		exit;
		
		
		print '<pre>';
		print_r($line);
		print_r($xmlobj);		
		print '</pre>';
		exit;
	}
	
	function getHttpRequest($call, $xmldata, $site='US')
	{
		$sites = $this->sitedetails();
		
		/* headers */
		$headers['X-EBAY-API-COMPATIBILITY-LEVEL'] = EBAY_COMPATLEVEL;
		$headers['X-EBAY-API-DEV-NAME']  = EBAY_DEVID;
		$headers['X-EBAY-API-APP-NAME']  = EBAY_APPID;
		$headers['X-EBAY-API-CERT-NAME'] = EBAY_CERTID;
		$headers['X-EBAY-API-CALL-NAME'] = $call;
		$headers['X-EBAY-API-SITEID']    = $sites[$site];
		
		/* xml data */
		$xml_request = '<?xml version="1.0" encoding="utf-8" ?>'."\n"
			. '<'.$call.'Request xmlns="urn:ebay:apis:eBLBaseComponents">'."\n"
			. '<WarningLevel>High</WarningLevel>'."\n"
			. $this->xml($xmldata, 1)
			. '</'.$call.'Request>'."\n";
		
		$date = 9999999999 - date("mdHis");
		file_put_contents(ROOT.'/app/tmp/apilogs/'
						  . $date.'.'.$call.'.'.$site.'.req.xml',
						  $xml_request);
		
		/* request object */
		$r = new HttpRequest();
		$r->setHeaders($headers);
		$r->setMethod(HttpRequest::METH_POST);
		$r->setOptions(array('timeout' => '60'));
		$r->setUrl(EBAY_SERVERURL);
		$r->setRawPostData($xml_request);
		
		return $r;
	}
	
	/**
	 * common function to call ebay api.
	 */
	function callapi($call, $xmldata, $site='US')
	{
		$r = $this->getHttpRequest($call, $xmldata, $site);
		
		$trycount = 0;
		while ($trycount < 5) {
			try {
				$r->send();
			} catch (HttpException $ex) {
				sleep(5);
				$trycount++;
				error_log($trycount.':'.$call);
				continue;
			}
			break;
		}
		
		$xml_response = $r->getResponseBody();
		$xmlobj = simplexml_load_string($xml_response);
		
		$date = 9999999999 - date("mdHis");
		file_put_contents(ROOT.'/app/tmp/apilogs/'
						  . $date.'.'.$call.'.'.$site.'.response.xml',
						  $xml_response);
		
		return $xmlobj;
	}
	
	
	/**
	 * RefURL: http://dev-forums.ebay.com/thread.jspa?threadID=500003564
	 */
	function duration2str($time, $rounding = TRUE) {
		
        preg_match("/P" .
				   "(?:(?P<year>[0-9]*)Y)?" . 
				   "(?:(?P<month>[0-9]*)M)?" .
				   "(?:(?P<day>[0-9]*)D)?" .
				   "(?:T" .
				   "(?:(?P<hour>[0-9]*)H)?" .
				   "(?:(?P<minute>[0-9]*)M)?" .
				   "(?:(?P<second>[0-9\.]*)S)?" .
				   ")?/s", $time, $d);
		
		$str = ($d['year']-0)
			. '-'.($d['month']-0)
			. '-'.($d['day']-0)
			. ' '.($d['hour']-0)
			. ':'.($d['minute']-0)
			. ':'.($d['second']-0);
		
        return $str;
    }
	
}
?>
