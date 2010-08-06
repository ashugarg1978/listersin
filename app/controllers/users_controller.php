<?
/**
 * todo:
 * - scrollbar appear when item detail expanded, width broken. [FIXED]
 * - use html5.
 * - unify json response to one code.
 * - i18n.
 * - scheduled additems by crontab.
 * - compress response body
 *
 */
class UsersController extends AppController {
	
	var $name = 'Users';    
	var $components = array('Auth', 'Email');
	//var $helpers = array('Cache');
	//var $cacheAction = null;
	
	var $user;
	var $accounts;
	var $filter;
	
	function beforeFilter()
	{
		error_log($this->here);
		if (!empty($_POST)) error_log('POST:'.print_r($_POST,1));
		parent::beforeFilter();
		
        $this->Auth->allow('index', 'register', 'receivenotify');
		$this->Auth->loginRedirect = array('controller' => 'users', 'action' => 'home');
		$this->Auth->fields = array('username' => 'email',  'password' => 'password');
		$this->user = $this->Auth->user();
		$this->set('user', $this->Auth->user());
		
		if (isset($this->user['User']['userid'])) {
			$this->accounts = $this->getAccounts($this->user['User']['userid']);
			Configure::write('Config.language', $this->user['User']['language']);
		}
		$this->set('accounts', $this->accounts);
		
		return;
    }	

	
	function receivenotify()
	{
		$xml = file_get_contents('php://input');
		
		$resfile = ROOT.'/app/tmp/apilogs/'.(9999999999-date("mdHis")).'.notify.xml';
		file_put_contents($resfile, $xml);
		chmod($resfile, 0777);
		
		$xml = preg_replace("/^.*<soapenv:Body>/s", "", $xml);
		$xml = preg_replace("/<\/soapenv:Body>.*$/s", "", $xml);
		$xmlobj = simplexml_load_string($xml);
		error_log($xmlobj->RecipientUserID
				  . ':'.$xmlobj->NotificationEventName
				  . ':'.$xmlobj->Item->ItemID);
		
		exit;
	}
	
	function index()
	{
		if (isset($this->user['User']['userid'])) {
			
			$sites = $this->sitedetails();
			foreach ($sites as $sitename => $siteid) {
				
				$hash[$sitename]['SiteID'] = $siteid;
				
				$hash[$sitename]['category']['name']          = array();
				$hash[$sitename]['category']['grandchildren'] = array();
				$hash[$sitename]['category']['features']      = array();
				
				$categorydata = $this->children($sitename, 0);
				$hash[$sitename]['category']['children'] = $categorydata['children'];
				if (isset($categorydata['name'])) {
					$hash[$sitename]['category']['name'] = $categorydata['name'];
				}
				
				// todo: get only frequentry used site by user.
				if ($sitename != 'US') continue;
				
				/* Shipping */
				$hash[$sitename]['ShippingType']
					= $this->getShippingType($sitename);
				
				$hash[$sitename]['ShippingServiceDetails']
					= $this->getShippingServiceDetails($sitename);
				
				$hash[$sitename]['ShippingPackageDetails']
					= $this->getShippingPackageDetails($sitename);
			}
			$hash['shippingmap'] = $this->getshippingmap();
			
			$this->set('hash', $hash);
			$this->set('summary', $this->getsummary());
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
        $this->redirect('/');
	}
	
	
	/**
	 * get summary data of items.
	 * todo: deleted items and empty trash function
	 * todo: use ebayuserid instead of accountid?
	 */
	function items()
	{
		/* check post parameters */
		$sql_filter = null;
		$sql_filter[] = "accountid IN (".implode(',', array_keys($this->accounts)).")";
		//$sql_filter[] = "id >= 2900";
		//$sql_filter[] = "ShippingDetails_ShippingServiceOptions != ''";
		$sql_filter[] = "ShippingDetails_ShippingType != 'Flat'";
		
		// todo: avoid sql injection
		if (!empty($_POST["id"]))
			$sql_filter[] = "id IN (".implode(",", $_POST['id']).")";
		
		if (!empty($_POST["ItemID"]))
			$sql_filter[] = "ItemID = '".$this->mres($_POST["ItemID"])."'";
		
		if (!empty($_POST["accountid"]))
			$sql_filter[] = "accountid = '".$this->mres($_POST["accountid"])."'";
		
		if (!empty($_POST["Title"]))
			$sql_filter[] = "Title LIKE '%".$this->mres($_POST["Title"])."%'";
		
		if (!empty($_POST['selling']) && isset($this->filter[$_POST['selling']]))
			$sql_filter[] = $this->filter[$_POST['selling']];
		
		$limit  = empty($_POST["limit"])  ? 10 : $_POST["limit"];
		$offset = empty($_POST["offset"]) ?  0 : $_POST["offset"];
		
		/* create sql statement */
		// todo: timezone convert.
		//. " CONVERT_TZ(items.ListingDetails_EndTime, 'GMT', 'Japan') AS ListingDetails_EndTime,"
		$sql = "SELECT SQL_CALC_FOUND_ROWS"
			. " id,"
			. " accountid,"
			. " ItemID,"
			. " ListingDetails_EndTime,"
			. " ListingDetails_ViewItemURL,"
			. " Title,"
			. " PictureDetails_PictureURL,"
			. " PrimaryCategory_CategoryID,"
			. " StartPrice,"
			. " Site,"
			. " SellingStatus_ListingStatus,"
			. " SellingStatus_QuantitySold,"
			. " Errors_LongMessage,"
			. " ShippingDetails_ShippingType,"
 		    . " schedule,"
			. " status"
			. " FROM items"
			. " WHERE ".implode(" AND ", $sql_filter);
		
		if (isset($_POST['sort']))
			$sort[] = $this->mres($_POST['sort']);
		
		$sort[] = "id DESC";
		$sql .= " ORDER BY ".implode(',', $sort);
		$sql .= " LIMIT ".$limit." OFFSET ".$offset;
		
		error_log($sql);
		$res = $this->User->query($sql);
		
		/* count total records */
		$res_cnt = $this->User->query("SELECT FOUND_ROWS() AS cnt");
		$cnt = $res_cnt[0][0]['cnt'];
		
		/* modify result records */
		foreach ($res as $idx => $row) {
			
			// when convert to another timezone
			//$row['items']['ListingDetails_EndTime'] = $row[0]['ListingDetails_EndTime'];
			
			$id = $row['items']['id'];
			$item = $row['items'];
			
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
			
			$tmppct = explode("\n", $item['PictureDetails_PictureURL']);
			if (is_array($tmppct))
				$item['PictureDetails_PictureURL'] = $tmppct[0];
			
			if (isset($item['schedule'])) {
				if (date('Y-m-d', strtotime($item['schedule'])) == date('Y-m-d')) {
					$item['schedule'] = date('H:i', strtotime($item['schedule']));
				} else {
					$item['schedule'] = date('M j', strtotime($item['schedule']));
				}
			}
			
			/* StartPrice */
			$item['StartPrice'] = number_format($item['StartPrice']);
			
			$item['Errors_LongMessage'] = explode("\n", $item['Errors_LongMessage']);
			
			/* add to rows */
			$items[] = $item;
		}
		
		$data['cnt'] = $cnt;
		if (isset($items))
			$data['res'] = $items;
		
		echo json_encode($data);
		//error_log(print_r($data,1));
		//error_log(json_encode($data));
		
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
		$row = $res[0]['items'];
		
		$site = $row['Site'];
		
		/* Picture */
		$row['PictureDetails_PictureURL'] = explode("\n", $row['PictureDetails_PictureURL']);
		if (isset($row['ShippingDetails_ShippingServiceOptions'])) {
			$row['ShippingDetails_ShippingServiceOptions']
				= unserialize($row['ShippingDetails_ShippingServiceOptions']);
		}
		
		/* category */
		$categoryid = $row['PrimaryCategory_CategoryID'];
		if ($categoryid > 0) {
			//$row['categoryfeatures'] = $this->categoryfeatures($site, $categoryid);
			
			$categorypath = $this->categorypath($site, $categoryid);
			$row['categorypath'] = array_keys($categorypath);
			$row['categorystr'] = implode(' > ', array_values($categorypath));
			$row['categorystr'] .= ' ('.implode(' > ', array_keys($categorypath)).')';
		}
		
		//$row['other']['site'] = $this->sitedetails();
		//$row['other']['shipping'] = $this->getshippingservice($row['Site']);
		
		//error_log(print_r($row,1));
		//error_log(json_encode($row));
		echo json_encode($row);
		
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
					. " = '".$this->mres(implode("\n", $arrurl))."'"
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
	
	// todo: check accountid
	function getsellerlist($ebayuserid)
	{
		system(ROOT.'/shells/kickdaemon.sh getsellerlist '.$ebayuserid);
		exit;
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
				. " '".$this->mres($_GET["username"])."',"
				. " '".$this->mres($_GET["ebaytkn"])."',"
				. " '".$this->mres($_GET["tknexp"])."',"
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
		error_log(print_r($res,1));
		
		$copycount = count($_POST['id']);
		
		$_POST = null;
		$_POST['limit'] = $copycount;
		$this->items();
		
		exit;
	}
	
	
	function save()
	{
		if (empty($_POST['id'])) return;
		$id = $_POST['id'];
		
		$sqlcol = null;
		foreach ($_POST as $k => $v) {
			
			if ($k == 'id') continue;
			
			// todo: don't skip these columns
			if ($k == 'ShippingType') continue;
			if ($k == 'ShippingCost') continue;
			
			if (is_array($v)) {
				$sqlcol[] = $k." = '".$this->mres(implode("\n", $v))."'";
			} else {
				$sqlcol[] = $k." = '".$this->mres($v)."'";
			}
		}
		
		// todo: check accountid
		$sql_update = "UPDATE items SET ".implode(", ", $sqlcol)." WHERE id = ".$id;
		$res = $this->User->query($sql_update);
		error_log($sql_update);
		if (mysql_error()) error_log('MySQL Error:'.mysql_error());
		
		$_POST = null;
		$_POST['id'] = $id;
		
		$this->item();
		
		exit;
	}
	
	
	/**
	 * delete items.
	 */
	function delete()
	{
		if (empty($_POST['id'])) return;
		
		$sql = "UPDATE items SET deleted = 1 WHERE id IN (".implode(",", $_POST['id']).")";
		$res = $this->User->query($sql);
		
		exit;
	}
	
	
	function relist()
	{
		if (empty($_POST['id'])) return;
		
		$opid = $this->user['User']['userid'].date('YmdHis');
		$opid = base_convert($opid, 10, 32);
		
		/* prepare additems */
		$sql = "UPDATE items"
			. " SET status = 'add.".$opid."'"
			. " WHERE status IS NULL"
			. " AND id IN (".implode(',', $_POST['id']).")"
			. " AND ItemID IS NULL";
		$res = $this->User->query($sql);
		
		/* prepare relistitem */
		// todo: check timezone
		$sql = "UPDATE items"
			. " SET status = 'relist.".$opid."'"
			. " WHERE status IS NULL"
			. " AND id IN (".implode(',', $_POST['id']).")"
			. " AND ItemID IS NOT NULL"
			. " AND ListingDetails_EndTime < NOW()";
		$res = $this->User->query($sql);
		
		system(ROOT.'/shells/kickdaemon.sh additems '.$opid);
		system(ROOT.'/shells/kickdaemon.sh relistitems '.$opid);
		
		return;
	}
	
	
	/**
	 * get hierarchical path data of specific category and its parents.
	 */
	function categorypath($site, $categoryid)
	{
		$table = "categories_".strtolower($site);
		$path = null;
		
		$parentid = $categoryid;
		while (true) {
			$res = $this->User->query("SELECT * FROM ".$table." WHERE CategoryID = ".$parentid);
			if (empty($res[0][$table])) break;
			
			$row = $res[0][$table];
			$path[$row['CategoryID']] = $row['CategoryName'];
			
			if ($row['CategoryLevel'] == 1) break;
			$parentid = $row['CategoryParentID'];
		}
		if (is_array($path)) ksort($path);
		
		return $path;
	}
	
	function grandchildren($site, $pathstr)
	{
		$data['name'] = array();
		$data['children'] = array();
		$data['grandchildren'] = array();
		$arrpath = explode('.', $pathstr);
		foreach ($arrpath as $i => $categoryid) {
			$data['grandchildren'][$categoryid] = 1;
			$p = $this->children($site, $categoryid);
			if (empty($p['children']) || $p['children'] == 'leaf') continue;
			foreach ($p['children'][$categoryid] as $i => $childid) {
				$c = $this->children($site, $childid);
				if (isset($c['children']) && is_array($c['children'])) {
					foreach ($c['children'] as $cid => $carr) {
						$data['children'][$cid] = $carr;
					}
				}
				if (isset($c['name'])) {
					foreach ($c['name'] as $cid => $cname) {
						$data['name'][$cid] = $cname;
					}
				}
			}
		}
		
		echo json_encode($data);
		//error_log(print_r($data,1));
		exit;
	}
	
	function children($site, $categoryid)
	{
		$data = null;
		$table = "categories_".strtolower($site);
		$sql = "SELECT CategoryID, CategoryName, LeafCategory FROM ".$table;
		if ($categoryid) {
			$sql .= " WHERE CategoryParentID = ".$categoryid
				. " AND CategoryID != ".$categoryid;
		} else {
			$sql .= " WHERE CategoryLevel = 1";
		}
		$res = $this->User->query($sql);
		if (count($res) > 0) {
			foreach ($res as $i => $row) {
				$id = $row[$table]['CategoryID'];
				$data['children'][$categoryid][] = $id;
				$data['name'][$id] = $row[$table]['CategoryName'];
				//$data['name'][$id] .= '('.$id.')';
				if ($row[$table]['LeafCategory']) {
					$data['children'][$id] = 'leaf';
				}
			}
		} else {
			$data['children'] = 'leaf';
		}
		
		//error_log(print_r($data,1));
		return $data;
	}
	
	function getShippingServiceDetails($sitename)
	{
		$xml = $this->readbz2xml(ROOT.'/data/apixml/ShippingServiceDetails/'.$sitename.'.xml.bz2');
		
		$xmlo = $xml->xpath("/ns:GeteBayDetailsResponse"
							. "/ns:ShippingServiceDetails"
							. "[ns:ValidForSellingFlow='true']");
		
		$arr = $this->xml2array($xmlo);
		
		return $arr['ShippingServiceDetails'];
	}
	
	function getShippingPackageDetails($sitename)
	{
		$xml = $this->readbz2xml(ROOT.'/data/apixml/ShippingPackageDetails/'.$sitename.'.xml.bz2');
		$arr = $this->xml2array($xml);
		return $arr['ShippingPackageDetails'];
	}
	
	
	/**
	 *
	 * todo: inherit features from its all parents.
	 */
	function categoryfeatures($site, $categoryid=null)
	{
		$xml = $this->readbz2xml(ROOT.'/data/apixml/CategoryFeatures/'.$site.'.xml.bz2');
		$ns = $xml->getDocNamespaces();
		
		/* DuationSet */
		$xmlobj_ld = $xml->xpath('/ns:GetCategoryFeaturesResponse'
								 . '/ns:FeatureDefinitions'
								 . '/ns:ListingDurations'
								 . '/ns:ListingDuration');
		foreach ($xmlobj_ld as $i => $o) {
			$attr = $o->attributes();
			$setid = $attr['durationSetID'].'';
			$dur = $o->children($ns['']);
			
			$a = null;
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
		//echo '<pre>'.print_r($durationset,1).'</pre>'; exit;
		
		/* SiteDefaults */
		$sdns = '/ns:GetCategoryFeaturesResponse/ns:SiteDefaults';
		
		$xmlobj_sd = $xml->xpath($sdns.'/ns:ListingDuration');
		foreach ($xmlobj_sd as $i => $o) {
			$attr = $o->attributes();
			$type = $attr['type'].'';
			$typedefault[$type] = $o.'';
		}
		
		$xmlobj_pm = $xml->xpath($sdns.'/ns:PaymentMethod');
		foreach ($xmlobj_pm as $i => $o) {
			if ($o.'' == 'CustomCode') continue;
			$arrpm[] = $o.'';
		}
		
		/* overwrite by child nodes */
		$path = null;
		if ($categoryid) {
			$path = $this->categorypath($site, $categoryid);
		}
		if (is_array($path)) {
			foreach ($path as $cid => $cname) {
				
				$cns = "/ns:GetCategoryFeaturesResponse/ns:Category[ns:CategoryID=".$cid."]";
				
				$ld = $xml->xpath($cns."/ns:ListingDuration");
				if ($ld) {
					foreach ($ld as $i => $o) {
						$attr = $o->attributes();
						$type = $attr['type'].'';
						$typedefault[$type] = $o.'';
					}
				}
				
				$pm = $xml->xpath($cns."/ns:PaymentMethod");
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
		
		$res['features'][$categoryid] = $data;
		echo json_encode($res);
		exit;
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
	
	function apixml($dir, $site)
	{
		$xml = $this->readbz2xml(ROOT.'/data/apixml/'.$dir.'/'.$site.'.xml.bz2');
		echo '<pre>'.print_r($xml,1).'</pre>';
		exit;
	}

	function getsummary()
	{
		/* summary of all accounts */
		foreach ($this->filter as $name => $filter) {
			$sql = "SELECT COUNT(*) AS cnt"
				. " FROM items"
				. " WHERE accountid IN (".implode(',', array_keys($this->accounts)).")"
				. " AND ".$filter;
			$res = $this->User->query($sql);
			foreach ($res as $i => $row) {
				$cnt = $row[0]['cnt'];
				$summary['all'][$name] = $cnt;
			}
		}
		
		/* each accounts */
		foreach ($this->filter as $name => $filter) {
			$sql = "SELECT accountid, COUNT(*) AS cnt"
				. " FROM items"
				. " WHERE accountid IN (".implode(',', array_keys($this->accounts)).")"
				. " AND ".$filter
				. " GROUP BY accountid";
			$res = $this->User->query($sql);
			foreach ($res as $i => $row) {
				$accountid = $row['items']['accountid'];
				$cnt = $row[0]['cnt'];
				$summary[$accountid][$name] = $cnt;
			}
		}
		
		//echo '<pre>'.print_r($summary,1).'</pre>';
		return $summary;
	}
	
	// todo: check UK:courier?
	function getShippingType($site)
	{
		if ($site == 'US') {
			$data['domestic']['Flat']       = 'Flat: same cost to all buyers';
			$data['domestic']['Calculated'] = 'Calculated: Cost varies by buyer location';
			$data['domestic']['Freight']    = 'Freight: large items over 150 lbs.';
			$data['domestic']['NoShipping'] = 'No shipping: Local pickup only';
		} else {
			$data['domestic']['Flat']       = 'Flat: same cost to all buyers';
			$data['domestic']['Calculated'] = 'Calculated: Cost varies by buyer location';
			$data['domestic']['NoShipping'] = 'No shipping: Local pickup only';
		}
		
		$data['international']['Flat']       = 'Flat: same cost to all buyers';
		$data['international']['Calculated'] = 'Calculated: Cost varies by buyer location';
		$data['international']['NoShipping'] = 'No shipping: Local pickup only';
		
		return $data;
	}
	
	// todo: reverse function
	function getshippingmap()
	{
		// todo: check "Freight" is only web?
		$data['Flat']['domestic']      = 'Flat';
		$data['Flat']['international'] = 'Flat';
		$data['Calculated']['domestic']      = 'Calculated';
		$data['Calculated']['international'] = 'Calculated';
		$data['FlatDomesticCalculatedInternational']['domestic']      = 'Flat';
		$data['FlatDomesticCalculatedInternational']['international'] = 'Calculated';
		$data['CalculatedDomesticFlatInternational']['domestic']      = 'Calculated';
		$data['CalculatedDomesticFlatInternational']['international'] = 'Flat';
		$data['FreightFlat']['domestic']      = 'Freight';
		$data['FreightFlat']['international'] = '???';
		
		return $data;
	}
}

?>
