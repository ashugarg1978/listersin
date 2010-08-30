<?php

class AppController extends Controller {
	
	var $uses = array('User');
	var $filter;
	
	function beforeFilter() {
		$this->filter = $this->getsqlfilter();
	}
	
	/**
	 * return array of Site => ID
	 */
	function sitedetails()
	{
		/* load xml */
		$xml = file_get_contents(ROOT.'/data/apixml/SiteDetails.xml');
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
	 * get column name of items table.
	 */
	function getitemcols()
	{
		$res = $this->User->query("DESC items;");
		foreach ($res as $i => $row) {
			if (preg_match('/@/', $row['COLUMNS']['Field'])) {
				$f['`'.$row['COLUMNS']['Field'].'`'] = $row;
			} else {
				$f[$row['COLUMNS']['Field']] = $row;
			}
		}
		
		return $f;
	}
	
	/**
	 * shorthand function of mysql_real_escape_string()
	 */
	function mres($string)
	{
		return mysql_real_escape_string($string);
	}
	
	
	// todo: avoid duplicate loop code in array and children
	function xml2array($xml)
	{
		if (is_array($xml)) {
			$array = null;
			foreach ($xml as $child) {
				$childname = $child->getName();
				if (isset($array[$childname])) {
					if (empty($dup[$childname])) {
						$dup[$childname] = true;
						$array[$childname] = array($array[$childname]);
					}
					$array[$childname][] = $this->xml2array($child);
				} else {
					$array[$childname] = $this->xml2array($child);
					foreach ($child->attributes() as $attr) {
						$array[$childname.'@'.$attr->getName()] = $attr.'';
					}
				}
			}
			return $array;
		} else if (count($xml->children())) {
			foreach ($xml->children() as $child) {
				$childname = $child->getName();
				if (isset($array[$childname])) {
					if (empty($dup[$childname])) {
						$dup[$childname] = true;
						$array[$childname] = array($array[$childname]);
					}
					$array[$childname][] = $this->xml2array($child);
				} else {
					$array[$childname] = $this->xml2array($child);
					foreach ($child->attributes() as $attr) {
						$array[$childname.'@'.$attr->getName()] = $attr.'';
					}
				}
			}
			return $array;
		} else {
			return $xml.'';
		}
	}
	
	
	function readbz2xml($file)
	{
		$xml = $this->bzread($file);
		$xmlobj = simplexml_load_string($xml);
		$ns = $xmlobj->getDocNamespaces();
		$xmlobj->registerXPathNamespace('ns', $ns['']);
		
		return $xmlobj;
	}
	
	
	function bzread($file)
	{
		$bz = bzopen($file, "r") or die("Couldn't open $file");
		
		$decompressed_file = '';
		while (!feof($bz)) {
			$decompressed_file .= bzread($bz, 4096);
		}
		bzclose($bz);
		
		return $decompressed_file;		
	}
	
	function getsqlfilter()
	{
		$filter['allitems'] = "deleted = 0";
		
		// todo: mix local and ebay scheduling items
		$filter['scheduled'] = "deleted = 0 AND ItemID IS NULL AND schedule > NOW()";
		
		$filter['active'] = "deleted = 0 AND ItemID IS NOT NULL AND ListingDetails_EndTime > NOW()";
		
		$filter['sold'] = "deleted = 0 AND ItemID IS NOT NULL AND SellingStatus_QuantitySold > 0";
		
		$filter['unsold'] = "deleted = 0 AND ItemID IS NOT NULL"
			. " AND ListingDetails_EndTime < Now()"
			. " AND SellingStatus_QuantitySold = 0";
		
		$filter['saved'] = "deleted = 0 AND ItemID IS NULL";
		
		$filter['trash'] = "deleted = 1";
		
		return $filter;
	}
	
	function getsellingquery()
	{
		$selling['allitems']['deleted'] = 0;
		
		$selling['scheduled']['deleted'] = 0;
		$selling['scheduled']['ItemID']['$exists'] = 0;
		
		$selling['active']['deleted'] = 0;
		$selling['active']['ItemID']['$exists'] = 1;
		$selling['active']['SellingStatus.ListingStatus'] = 'Active';
		//$selling['active']['ListingDetails.EndTime']['$gte'] = date('Y-m-d').'T'.date('H:i:s');
		
		$selling['sold']['deleted'] = 0;
		$selling['sold']['ItemID']['$exists'] = 1;
		$selling['sold']['SellingStatus.QuantitySold']['$gte'] = "1";
		
		$selling['unsold']['deleted'] = 0;
		$selling['unsold']['ItemID']['$exists'] = 1;
		$selling['unsold']['SellingStatus.ListingStatus'] = 'Completed';
		$selling['unsold']['SellingStatus.QuantitySold'] = "0";
		//$selling['unsold']['ListingDetails.EndTime']['$lte'] = date('Y-m-d').'T'.date('H:i:s');
		
		$selling['saved']['deleted'] = 0;
		$selling['saved']['ItemID']['$exists'] = 0;
		
		$selling['trash']['deleted'] = 1;
		
		return $selling;
	}
	
	/**
	 * http://www.xe.com/symbols.php
	 */
	function currencysymbols($str)
	{
		$cs['USD'] = 'US $';
		$cs['AUD'] = 'AU $';
		$cs['CAD'] = '$';
		$cs['EUR'] = 'EUR '; // '€';
		$cs['GBP'] = '£';
		$cs['JPY'] = '¥';
		$cs['CHF'] = 'CHF ';
		//$cs[''] = '';
		
		if (isset($cs[$str])) {
			return $cs[$str];
		} else {
			return $str;
		}
	}
}

?>
