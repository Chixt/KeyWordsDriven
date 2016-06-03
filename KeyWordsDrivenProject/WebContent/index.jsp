<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title	>index page</title>

<style type="text/css">
@import "/DojoSource/dojo/resources/dojo.css";
@import "/DojoSource/dijit/themes/claro/claro.css";
@import "/DojoSource/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css";
@import "/DojoSource/dojox/grid/enhanced/resources/claro/EnhancedGrid.css";
@import "/keyWordsDriven/js/resources/testCase.css";
</style>

<!-- Load Dojo -->
<script type="text/javascript">
dojoConfig = {
	async:true,
	cacheBust: "v=1.1",
	parseOnLoad: false,
	 packages:
	        [
	            {
                   name:  "keyWordsDriven",
	               location: "/keyWordsDriven/js/keyWordsDriven"
	             }                     
	        ]
};
</script>

<script type="text/javascript" src="/DojoSource/dojo/dojo.js"></script>
<script type="text/javascript">
require([
         "dojo/dom",
         "dojo/dom-construct",
         "dojo/_base/window",
         "keyWordsDriven/TestCase/createTestCase",
         "dojo/parser",
         "dojo/domReady!"
     ], function (
    		 dom,
    		 domConstruct,
    		 baseWindow,
    		 createTestCase,
    		 parser,
    		 domReady
     )
     {	
	
	var caseList = new createTestCase();
	caseList.placeAt(baseWindow.body());
	 
	
	
     });
</script>
</head>
<body class="claro">
<div id="gridDiv"></div>
<div id="btn"></div>
</body>
</html>