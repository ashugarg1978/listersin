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
	
	function test()
	{
		$sql = "SELECT accountid, ";
	}
	
	function beforeFilter() {
		
		error_log($this->action.' POST:'.print_r($_POST,1));
		
        $this->Auth->allow('index', 'register');
		$this->Auth->loginRedirect = array('controller' => 'users', 'action' => 'home');
		$this->Auth->fields = array('username' => 'email',  'password' => 'password');
		$this->user = $this->Auth->user();
		$this->set('user', $this->Auth->user());
		
		if (isset($this->user['User']['userid'])) {
			$this->accounts = $this->getAccounts($this->user['User']['userid']);
			Configure::write('Config.language', $this->user['User']['language']);
		}
		$this->set('accounts', $this->accounts);

		//$this->filter[''];
    }	
	
	function index()
	{
		if (isset($this->user['User']['userid'])) {
			
			$hash['site'] = $this->sitedetails();
			foreach ($hash['site'] as $sitename => $siteid) {
				$hash['shipping'][$sitename] = $this->getshippingservice($sitename);
				//$category[$sitename] = $this->childcategories($sitename);
			}
			
			/*
			  $hash['category'] = $category;
			*/
			
			$this->set('hash', $hash);
			
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
			$sql_filter[] = "ItemID = '".$this->mres($_POST["ItemID"])."'";
		
		if (!empty($_POST["accountid"]))
			$sql_filter[] = "accountid = '".$this->mres($_POST["accountid"])."'";
		
		if (!empty($_POST["Title"]))
			$sql_filter[] = "Title LIKE '%".$this->mres($_POST["Title"])."%'";
		
		//$sql_selling['scheduled'] = "ListingDetails_StartTime > NOW()";
		$sql_selling['scheduled'] = "schedule > NOW()";
		
		$sql_selling['active'] = "ItemID IS NOT NULL AND ListingDetails_EndTime > Now()";
		
		$sql_selling['sold'] = "ItemID IS NOT NULL AND SellingStatus_QuantitySold > 0";
		
		$sql_selling['unsold'] = "ItemID IS NOT NULL"
			. " AND ListingDetails_EndTime < Now()"
			. " AND SellingStatus_QuantitySold = 0";
		
		$sql_selling['saved'] = "ItemID IS NULL";
		
		if (!empty($_POST['selling']) && $_POST['selling'] != 'all') {
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
 		    . " items.schedule,"
			. " items.status"
			. " FROM items"
			. " JOIN accounts USING (accountid)"
			. " WHERE ".implode(" AND ", $sql_filter);
		
		if (isset($_POST['sort'])) {
			$sort[] = $this->mres($_POST['sort']);
		}
		$sort[] = "id DESC";
		$sql .= " ORDER BY ".implode(',', $sort);
		$sql .= " LIMIT ".$limit." OFFSET ".$offset;
		
		//error_log($sql);
		$res = $this->User->query($sql);
		
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
			
			$tmppct = explode("\n", $item['PictureDetails_PictureURL']);
			if (is_array($tmppct)) {
				$item['PictureDetails_PictureURL'] = $tmppct[0];
			}
			
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
		if (isset($items)) {
			$data['res'] = $items;
		}
		
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
		$data = $res[0]['items'];
		
		$data['PictureDetails_PictureURL'] = explode("\n", $data['PictureDetails_PictureURL']);
		if (isset($data['ShippingDetails_ShippingServiceOptions'])) {
			$data['ShippingDetails_ShippingServiceOptions']
				= unserialize($data['ShippingDetails_ShippingServiceOptions']);
		}
		
		// todo: avoid infinite loop
		$cid = $data['PrimaryCategory_CategoryID'];
		if ($cid > 0) {
			$data['categorypath']     = $this->categorypath($data['Site'], $cid);
			$data['categoryfeatures'] = $this->categoryfeatures($data['Site'], $cid);
		}
		
		//$data['other']['site'] = $this->sitedetails();
		//$data['other']['shipping'] = $this->getshippingservice($data['Site']);
		
		error_log(print_r($data,1));
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
			if (is_array($v)) {
				$sqlcol[] = $k." = '".$this->mres(implode("\n", $v))."'";
			} else {
				$sqlcol[] = $k." = '".$this->mres($v)."'";
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
			$arr[] = $k." = '".$this->mres($v)."'";
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
	 * get child and grandchild categories
	 */
	function childcategories($site='US', $categoryid=null)
	{
		//$this->cacheAction = '1 hour';
		
		if (isset($_POST['site'])      ) $site       = $_POST['site'];
		if (isset($_POST['categoryid'])) $categoryid = $_POST['categoryid'];

		/* read chache */
		$cachename = 'child_'.$site.'_'.$categoryid;
		$data = Cache::read($cachename);
		if ($data !== false) return $data;
		
		
		$xmlobj = $this->readbz2xml(ROOT.'/data/apixml/CategoryFeatures.'.$site.'.xml.bz2');
		
		/* DuationSet */
		$xmlobj_ld = $xmlobj->xpath('/ns:GetCategoryFeaturesResponse'
									. '/ns:FeatureDefinitions'
									. '/ns:ListingDurations'
									. '/ns:ListingDuration');
		foreach ($xmlobj_ld as $i => $o) {
			$attr = $o->attributes();
			$setid = $attr['durationSetID'].'';
			$dur = $o->children();
			
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
		//echo '<pre>'.print_r($durationset,1).'</pre>';exit;
		
		
		$data = array();
		$table = "categories_".strtolower($site);
		$filter = null;
		if (empty($categoryid)) {
			$filter[] = "CategoryLevel = 1";
		} else {
			$filter[] = "CategoryParentID = ".$categoryid;
			$filter[] = "CategoryID != ".$categoryid;
		}
		$sql = "SELECT CategoryID, CategoryName, LeafCategory"
			. " FROM ".$table." WHERE ".implode(" AND ", $filter);
		$res = $this->User->query($sql);
		if (count($res) > 0) {
			foreach ($res as $i => $row) {
				$childid = $row[$table]['CategoryID'];
				
				/* grandchildren */
				$grandchildren = null;
				$sqlgc = "SELECT CategoryID, CategoryName, LeafCategory"
					. " FROM ".$table
					. " WHERE CategoryParentID = ".$childid
					. " AND CategoryID != ".$childid;
				$resgc = $this->User->query($sqlgc);
				if (count($resgc) > 0) {
					foreach ($resgc as $j => $rowgc) {
						$grandchildren[] = $rowgc[$table];
					}
					
					$row[$table]['children'] = $grandchildren;
				}
				
				$children[] = $row[$table];
			}
			
			$data['categories'] = $children;
		}
		
		//echo '<pre>'.print_r($data,1).'</pre>'; exit;
		//echo json_encode($data);exit;
		Cache::write($cachename, $data);
		return $data;
	}
	
	/**
	 * 
	 */
	function category($site='US', $categoryid=null)
	{
		if (isset($_POST['site'])      ) $site       = $_POST['site'];
		if (isset($_POST['categoryid'])) $categoryid = $_POST['categoryid'];
		
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
	
	
	function getshippingservice($sitename)
	{
		/* load xml */
		$xml = file_get_contents(ROOT.'/data/apixml/eBayDetails.'.$sitename.'.xml');
		$xmlobj = simplexml_load_string($xml);
		$ns = $xmlobj->getDocNamespaces();
		$xmlobj->registerXPathNamespace('ns', $ns['']);
		
		$xmlobjo = $xmlobj->xpath
			('/ns:GeteBayDetailsResponse'
			 . '/ns:ShippingServiceDetails'
			 . "[ns:ServiceType='Calculated']"
			 . "[ns:ShippingServiceID<50000]"
			 . "[ns:ValidForSellingFlow='true']");
		
		$arr = null;
		foreach ($xmlobjo as $o) {
			$arr[] = $o->Description.'';
		}
		
		//echo '<pre>'.print_r($arr,1).'</pre>';exit;
		return $arr;
	}
	
	/**
	 *
	 * todo: inherit features from its all parents.
	 */
	function categoryfeatures($site, $categoryid=null)
	{
		/* load xml */
		$xml = file_get_contents(ROOT.'/data/apixml/CategoryFeatures.xml');
		$xmlobj = simplexml_load_string($xml);
		$ns = $xmlobj->getDocNamespaces();
		$xmlobj->registerXPathNamespace('ns', $ns['']);
		
		/* DuationSet */
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
