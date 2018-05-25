var newsUtils={
		getUrl:function(){
	        //var flag=true;
	        var flag=false;
	        function getBaseUrl(flag){
	            var baseUrl={};
	            if(flag){
	                baseUrl={
	                        url:'http://118.190.133.146:8091'	
	                }
	            }else{
	                baseUrl={
	                        url:'http://192.168.9.4:8011'	
	                    }
	            }
	            return baseUrl;
	       }
	        return getBaseUrl(flag);  
	    }
}