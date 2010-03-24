<?

class EbayController extends AppController
{
	var $uses = array('Db');
	var $layout = 'ebay';
	var $components = array('Auth');
	
	var $r;
	
	function items()
	{
		$sql = "SELECT *"
			. " FROM items"
			. " ORDER BY itemid DESC";
		$res = $this->Db->query($sql);
		
		$this->set('items', $res);
	}
	
	function index()
	{
		$sql = "SELECT *"
			. " FROM items"
			. " ORDER BY itemid DESC";
		$res = $this->Db->query($sql);
		print_r($res);
		
		exit;
	}
	
	function accept()
	{
		$sql_insert = "INSERT INTO accounts (userid, ebayuserid, ebaytoken, ebaytokenexp, created)"
			. " VALUES ("
			. " 1,"
			. " '".mysql_real_escape_string($_GET["username"])."',"
			. " '".mysql_real_escape_string($_GET["ebaytkn"])."',"
			. " '".mysql_real_escape_string($_GET["tknexp"])."',"
			. " NOW())";
		$res = $this->Db->query($sql_insert);
	}
	
	function reject()
	{
		
	}
	
	function submit($id)
	{
		$this->r = new HttpRequest();
		
        $devID  = 'e60361cd-e306-496f-ad7d-ba7b688e2207';
        $appID  = 'Yoshihir-1b29-4aad-b39f-1be3a37e06a7';
        $certID = '8118c1eb-e879-47f3-a172-2b08ca680770';
		
		$headers['X-EBAY-API-COMPATIBILITY-LEVEL'] = 585;
		$headers['X-EBAY-API-DEV-NAME']  = $devID;
		$headers['X-EBAY-API-APP-NAME']  = $appID;
		$headers['X-EBAY-API-CERT-NAME'] = $certID;
		$headers['X-EBAY-API-CALL-NAME'] = 'AddItems';
		$headers['X-EBAY-API-SITEID'] = 0;
		$this->r->setHeaders($headers);
		
		$data2 = file_get_contents("/var/www/dev.xboo.st/app/tmp/x.xml");
		
        $url = 'https://api.sandbox.ebay.com/ws/api.dll';
		
		header("Content-Type: application/xml");
		$xml = $this->post($url, $data2);
		
		print_r($xml);
		exit;
	}
	
	function get($url)
	{
		$this->r->setMethod(HttpRequest::METH_GET);
		$this->r->setUrl($url);
		$this->r->send();
		
		$html = $this->r->getResponseBody();
		
		return $html;
	}
	
	function post($url, $form)
	{
		$this->r->setMethod(HttpRequest::METH_POST);
		$this->r->setUrl($url);
		$this->r->setRawPostData($form);
		$this->r->send();
		
		$html = $this->r->getResponseBody();
		
		return $html;
	}
	
}
?>
