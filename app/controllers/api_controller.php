<?
/**
 * todo:
 * - scheduled additems by crontab.
 *
 */
class ApiController extends AppController {
	
	//var $uses = array('User');
    var $name = 'Users';    
    //var $components = array('Auth', 'Email');
	//var $user;
	//var $accounts;
	
	//var $uses = array('Db');
    //var $components = array('Util');
	
	function beforeFilter()
	{
		error_log('api:'.$this->action);
		parent::beforeFilter();
	}
	
	function test($arg=null)
	{
		$mongo = new Mongo();
		$query['UserID']['$in'] = array('testuser_aichi', 'testuser_chiba');
		$fields['UserID'] = 1;
		$fields['Title'] = 1;
		$cursor = $mongo->ebay->items->find($query, $fields)->limit(700);
		error_log(print_r($cursor,1));
		$tmparr = iterator_to_array($cursor);
		error_log(print_r($tmparr,1));
		exit;
		
		if (true) {
			$xml = file_get_contents(ROOT.'/app/tmp/apilogs/9276839971.notify.xml');
			$xml = preg_replace("/^.*<soapenv:Body>/s", "", $xml);
			$xml = preg_replace("/<\/soapenv:Body>.*$/s", "", $xml);
			
			//$arr = null;
			//$parser = xml_parser_create('');
			//xml_parse($parser, $xml, $arr);
			//echo print_r($arr,1);exit;
			
			//$xml = str_replace("\n", "", $xml);
			//$o = new SoapVar($xml, XSD_STRING);
			//$o = new SoapVar($xml, XSD_ANYXML);
			$xmlobj = simplexml_load_string($xml);
			echo print_r($xmlobj,1);
			exit;
			
			$xmlobj = simplexml_load_file(ROOT.'/app/tmp/apilogs/9273909998.AddItems.Canada.req.xml');
			echo print_r($xmlobj,1);
			exit;
		}
		
		$sql = "SELECT * FROM accounts WHERE ebayuserid = 'testuser_chiba'";
		$res = $this->User->query($sql);
		$account = $res[0]['accounts'];
		
		if ($arg == 'get') {
			
			$h = null;
			$h['RequesterCredentials']['eBayAuthToken'] = $account['ebaytoken'];
			$h['PreferenceLevel'] = 'Application';
			$h['PreferenceLevel'] = 'Event';
			$h['PreferenceLevel'] = 'User';
			
			$res = $this->callapi('GetNotificationPreferences', $h);
			
		} else {
		
			$h = null;
			$h['RequesterCredentials']['eBayAuthToken'] = $account['ebaytoken'];
			$h['ApplicationDeliveryPreferences']['ApplicationEnable'] = 'Enable';
			$h['ApplicationDeliveryPreferences']['ApplicationURL'] =
				'http://175.41.130.89/users/receivenotify';
			$events = array('ItemListed',
							'EndOfAuction',
							'ItemClosed',
							'ItemExtended',
							'ItemRevised',
							'ItemSold',
							'ItemUnsold');
			foreach ($events as $i => $event) {
				$ne['EventType'] = $event;
				$ne['EventEnable'] = 'Enable';
				$h['UserDeliveryPreferenceArray']['NotificationEnable'][] = $ne;
			}
			
			$res = $this->callapi('SetNotificationPreferences', $h);
			
		}
		
		echo '<pre>'.print_r($res,1).'</pre>';
		
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
		if (empty($ids)) {
			// If called from browser, kick background process.
			if (isset($_POST['id'])) {
				$cmd = 'PATH=/usr/local/php/bin '.ROOT.'/cake/console/cake'
					. ' -app '.ROOT.'/app daemon enditems '.implode(',', $_POST['id'])
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
			. " AND ItemID IS NOT NULL";
		$res = $this->User->query($sql);
		
		$sql = "SELECT * FROM items"
			. " JOIN accounts USING (accountid)"
			. " WHERE id IN (".implode(",", $ids).")"
			. " AND ItemID IS NOT NULL";
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
				$chunked = array_chunk($hash, 10);
				foreach ($chunked as $chunkedidx => $items) {
					
					$seqidx++;
					$h = null;
					$h['RequesterCredentials']['eBayAuthToken'] =
						$accounts[$accountid]['ebaytoken'];
					
					foreach ($items as $i => $arr) {
						$h['EndItemRequestContainer'][$i]['MessageID'] = ($i+1);
						$h['EndItemRequestContainer'][$i]['EndingReason'] = 'NotAvailable';
						$h['EndItemRequestContainer'][$i]['ItemID'] = $arr['ItemID'];
						$seqmap[$seqidx][($i+1)] = $arr['id'];
					}
					
					$r = $this->getHttpRequest('EndItems', $h, $site);
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
				$sql = "UPDATE items SET status = 0, Errors_LongMessage ="
					. " '".date('m.d H:i')." Network error. Please try again later.'"
					. " WHERE id IN (".implode(",", $seqmap[$ridx]).")";
				$this->User->query($sql);
				
				continue;
			}
			
			$xml_response = $r->getResponseBody();
			
			$resfile = ROOT.'/app/tmp/apilogs/'
				. (9999999999-date("mdHis")).'.EndIs.res.'.substr('0'.$ridx, -2).'.xml';
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
					$j['SellingStatus_ListingStatus'] = ''; // manually?
					$j['ListingDetails_StartTime'] = $obj->StartTime;
					$j['ListingDetails_EndTime']   = $obj->EndTime;
					
				} else if (isset($obj->Errors)) {
					
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
	
	function relistitems($opid)
	{
		$sites = $this->sitedetails();
		
		// read item data from database
		// todo: check user account id
		$sql = "SELECT * FROM items"
			. " JOIN accounts USING (accountid)"
			. " WHERE status = 'relist.".$opid."'"
			. " AND ItemID IS NOT NULL"
			. " AND ListingDetails_EndTime < NOW()";
		$res = $this->User->query($sql);
		if (empty($res)) return;
		error_log('relist.'.$opid.' : '.count($res).' items.');
		
		foreach ($res as $i => $arr) {
			
			$accounts = $arr['accounts'];
			$items    = $arr['items'];
			
			$h = null;
			$h['RequesterCredentials']['eBayAuthToken'] = $accounts['ebaytoken'];
			$h['Item'] = $this->xml_item($items);
			
			$rsp = $this->callapi('RelistItem', $h);
		}
		
		return;
	}
	
	function addscheduleditems()
	{
		$opid = '000'.date('YmdHis');
		$opid = base_convert($opid, 10, 32);
		
		$sql = "UPDATE items"
			. " SET status = 'add.".$opid."'"
			. " WHERE status IS NULL"
			. " AND schedule != '0000-00-00 00:00:00'"
			. " AND schedule < NOW()"
			. " AND ItemID IS NULL";
		$res = $this->User->query($sql);
		$this->additems($opid);
		
		$sql = "UPDATE items"
			. " SET status = 'relist.".$opid."'"
			. " WHERE status IS NULL"
			. " AND relist = 1"
			. " AND ListingDetails_EndTime < NOW()"
			. " AND ItemID IS NOT NULL"
			. " LIMIT 1";
		$res = $this->User->query($sql);
		$this->relistitems($opid);
		
		return;
	}
	
	
	// todo: don't post over 18 simaltenious ruled by ebay guildline.
	function additems($opid)
	{
		exit;
		
		$sites = $this->sitedetails();
		
		$this->mongo = new Mongo();
		
		/* get items */
		$cursor = $this->mongo->ebay->items->find()->limit(600);
		$items = iterator_to_array($cursor);
		foreach ($items as $i => $item) {
			$userid = $item['UserID'];
			$site   = $item['Site'];
			$itemdata[$userid][$site]['items'][] = $item;
		}
		error_log(print_r(array_keys($itemdata),1));
		
		/* get token */
		// todo: get only needed userid
		//$query = null;
		//$query['userids.$.username']['$in'] = array_keys($itemdata);
		//$fields['userids'] = 1; 
		$users = $this->mongo->ebay->users->findOne();
		//$users = iterator_to_array($cursor);
		
		/* chunk by 5 */
		foreach ($itemdata as $userid => $sitearr) {
			foreach ($sitearr as $site => $itemarr) {
				$chunked = array_chunk($itemarr['items'], 5);
				foreach ($chunked as $chunkidx => $chunkarr) {
					$chunk = null;
					$chunk['userid'] = $userid;
					$chunk['site']   = $site;
					$chunk['items']  = $chunkarr;
					$chunks[] = $chunk;
				}
			}
		}
		
		/* chunk by 18 */
		$chunk18 = array_chunk($chunks, 18);
		foreach ($chunk18 as $chunk18idx => $chunk5) {
			
			$pool = new HttpRequestPool();
			
			foreach ($chunk5 as $chunk5idx => $chunk) {
				error_log('chunk '.$chunk18idx.'-'.$chunk5idx.' '.count($chunk['items']));
				error_log(print_r($chunk,1));
				
				$h = null;
				$h['RequesterCredentials']['eBayAuthToken'] =
					$users['userids'][$chunk['userid']]['ebaytkn'];
				
				foreach ($chunk['items'] as $i => $item) {
					$h['AddItemRequestContainer'][$i]['MessageID'] = ($i+1);
					$h['AddItemRequestContainer'][$i]['Item'] = $item;
				}
				$r = $this->getHttpRequest('AddItems', $h, $chunk['site']);
				$pool->attach($r);
			}
			
			try {
				$pool->send();
			} catch (HttpRequestPoolException $ex) {
				error_log("pool->send() error\n".$ex->getMessage());
			}
			
			$ridx = 0;
			foreach ($pool as $r) {
				
				$ridx++;
				
				$xml_response = $r->getResponseBody();
				
				$resfile = ROOT.'/app/tmp/apilogs/'
					. (9999999999-date("mdHis")).'.AddIs.res.'.$chunk18idx.'.'.$chunk5idx.'.xml';
				file_put_contents($resfile, $xml_response);
				chmod($resfile, 0777);
			}
			
		}
		
		exit;
		
		/* read item data from database */
		// todo: check user account id
		// todo: avoid duplicate listing submit
		$sql = "SELECT * FROM items"
			. " JOIN accounts USING (accountid)"
			. " WHERE status = 'add.".$opid."'"
			. " AND ItemID IS NULL";
		$res = $this->User->query($sql);
		if (empty($res)) return;
		error_log('add.'.$opid.' : '.count($res).' items.');
		
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
				$sql = "UPDATE items SET status = NULL,"
					. " Errors_LongMessage ="
					. " '".date('m.d H:i')." Network error. Please try again later.'"
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
				$j['status'] = 'NULL';
				$j['schedule'] = '0000-00-00 00:00:00';
				
				if (isset($obj->ItemID)) {
					
					$j['ItemID'] = $obj->ItemID;
					$j['SellingStatus_ListingStatus'] = 'Active'; // manually?
					$j['ListingDetails_StartTime'] = $obj->StartTime;
					$j['ListingDetails_EndTime']   = $obj->EndTime;
					
				} else if (isset($obj->Errors)) {
					
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
					if ($v == 'NULL') {
						$sql_u[] = $f." = NULL";
					} else {
						$sql_u[] = $f." = '".mysql_real_escape_string($v)."'";
					}
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
	
	
	function getebaydetails()
	{
		$sql = "SELECT * FROM accounts WHERE ebayuserid = 'testuser_hal'";
		$res = $this->User->query($sql);
		$account = $res[0]['accounts'];
		
		$h = null;
		$h['RequesterCredentials']['eBayAuthToken'] = $account['ebaytoken'];
		//$h['DetailName'] = 'ShippingServiceDetails';
		
		$sites = $this->sitedetails();
		foreach ($sites as $sitename => $siteid) {
			$xmlobj = $this->callapi('GeteBayDetails', $h, $sitename);
		}
		
		return;
	}
	
	function importxml()
	{
		$mongo = new Mongo();
		
		$sites = $this->sitedetails();
		foreach ($sites as $sitename => $siteid) {
			echo "importxml:".$sitename."\n";
			
			/* Categories */
			if (true) {
				$xml = $this->readbz2xml(ROOT.'/data/apixml/Categories/'.$sitename.'.xml.bz2');
				$arr = $this->xml2array($xml->CategoryArray);
				
				eval('$coll = $mongo->ebay->Categories_'.$sitename.';');
				$coll->drop();
				$coll->batchInsert($arr['Category']);
			}
			
		}
	}
	
	function updatexml()
	{
		$mongo = new Mongo();
		
		$sql = "SELECT * FROM accounts WHERE ebayuserid = 'testuser_tokyo'";
		$res = $this->User->query($sql);
		$token = $res[0]['accounts']['ebaytoken'];
		
		$sites = $this->sitedetails();
		foreach ($sites as $sitename => $siteid) {
			echo $sitename."\n";
			//if ($sitename != 'Ireland') continue;
			
			/* Categories */
			if (false) {
				$h = null;
				$h['RequesterCredentials']['eBayAuthToken'] = $token;
				$h['CategorySiteID'] = $siteid;
				$h['DetailLevel'] = 'ReturnAll';
				
				$xmlobj = $this->callapi("GetCategories", $h, $sitename);
				
				exit;
			}
			
			/* ShippingServiceDetails */
			if (false) {
				//$tmp = 'ShippingServiceDetails';
				$tmp = 'ShippingPackageDetails';
				$h = null;
				$h['RequesterCredentials']['eBayAuthToken'] = $token;
				$h['DetailName'] = $tmp;
				$r = $this->getHttpRequest('GeteBayDetails', $h, $sitename);
				
				$trycount = 0;
				while ($trycount < 5) {
					try {
						$r->send();
					} catch (HttpException $ex) {
						sleep(5);
						$trycount++;
						error_log($trycount.':updatexml');
						continue;
					}
					break;
				}
				
				$xml = $r->getResponseBody();
				
				file_put_contents(ROOT.'/data/apixml/'.$tmp.'/'.$sitename.'.xml', $xml);
			}
			
			/* GetCategoryFeatures */
			if (false) {
				$h = null;
				$h['RequesterCredentials']['eBayAuthToken'] = $token;
				$h['DetailLevel'] = 'ReturnAll';
				$h['ViewAllNodes'] = 'true';
				$r = $this->getHttpRequest('GetCategoryFeatures', $h, $sitename);
				$r->send();
				$xml = $r->getResponseBody();
				file_put_contents(ROOT.'/data/apixml/CategoryFeatures.'.$sitename.'.xml', $xml);
			}
			
		}
		
		return;
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
	
	function getsellerlists_redo()
	{
		$account['accountid'] = 14;
		
		$xml = file_get_contents(ROOT.'/app/tmp/apilogs/9182846342.GetSellerList.US.response.xml');
		$xmlobj = simplexml_load_string($xml);
		echo print_r($xmlobj,1);
		$this->getsellerlist_import($xmlobj, $account);
		
	}
	
	/**
	 *   - getsellerlists
	 *   - getsellerlist
	 */
	function getsellerlists()
	{
		$mongo = new Mongo();
		
		$cursor = $mongo->ebay->users->find();
		$rows = iterator_to_array($cursor);
		foreach ($rows as $i => $row) {
			if (!is_array($row['userids'])) continue;
			foreach ($row['userids'] as $userid => $userobj) {
				$this->getsellerlist($userid);
			}
		}
		
		return;
	}
	
	// todo: authorize login user or daemon process
	function getsellerlist($ebayuserid, $userid=null)
	{
		$mongo = new Mongo();
		
		/* get ebay token */
		$query['userids.'.$ebayuserid]['$exists'] = 1;
		$field['userids.'.$ebayuserid.'.ebaytkn'] = 1;
		$row = $mongo->ebay->users->findOne($query, $field);
		$token = $row['userids'][$ebayuserid]['ebaytkn'];
		
		
		// todo: I can specify the span by EndTime
		$h = null;
		$h['RequesterCredentials']['eBayAuthToken'] = $token;
		//$h['GranularityLevel'] = 'Fine'; // Coarse, Medium, Fine
		$h['DetailLevel'] = 'ReturnAll'; // ItemReturnDescription, ReturnAll
		$h['StartTimeFrom'] = '2010-06-01 00:00:00';
		$h['StartTimeTo'] =
			date('Y-m-d H:i:s', strtotime('+90day', strtotime($h['StartTimeFrom'])));
		$h['Pagination']['EntriesPerPage'] = 50;
		$h['Pagination']['PageNumber'] = 1;
		$h['Sort'] = 1;
		if ($userid) $h['UserID'] = $userid;
		
		while (true) {
			$xmlobj = $this->callapi('GetSellerList', $h);
			
			foreach ($xmlobj->ItemArray->Item as $idx => $o) {
				
				$tmparr = $this->xml2array($o);
				$tmparr['UserID'] = $xmlobj->Seller->UserID.'';
				$tmparr['deleted'] = 0;
				
				$mcnt = $mongo->ebay->items->count(array('ItemID' => $tmparr['ItemID']));
				
				$mongo->ebay->items->update(array('ItemID' => $tmparr['ItemID']),
											$tmparr,
											array('upsert' => true));
			}
			
			$tnop = $xmlobj->PaginationResult->TotalNumberOfPages;
			$tnoe = $xmlobj->PaginationResult->TotalNumberOfEntries;
			
			error_log('getsellerlist : '.$ebayuserid.' ('.$tnoe.')'
					  . ' '.$h['Pagination']['PageNumber'].'/'.$tnop);
			
			if ($xmlobj->HasMoreItems == 'true') {
				$h['Pagination']['PageNumber']++;
			} else {
				break;
			}
		}
		
		return;
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
		if ($sites[$site] == 100) {
			$headers['X-EBAY-API-SITEID'] = 0;
		} else {
			$headers['X-EBAY-API-SITEID'] = $sites[$site];
		}
		
		/* xml data */
		$xml_request = '<?xml version="1.0" encoding="utf-8" ?>'."\n"
			. '<'.$call.'Request xmlns="urn:ebay:apis:eBLBaseComponents">'."\n"
			. '<WarningLevel>High</WarningLevel>'."\n"
			. $this->array2xml($xmldata, 1)
			. '</'.$call.'Request>'."\n";
		
		$date = 9999999999 - date("mdHis");
		file_put_contents(ROOT.'/app/tmp/apilogs/'
						  . $date.'.'.$call.'.'.$site.'.req.xml',
						  $xml_request);
		
		/* request object */
		$r = new HttpRequest();
		$r->setHeaders($headers);
		$r->setMethod(HttpRequest::METH_POST);
		$r->setOptions(array('timeout' => '120'));
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
	
}
?>
