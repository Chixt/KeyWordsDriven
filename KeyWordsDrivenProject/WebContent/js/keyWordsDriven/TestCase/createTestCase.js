define(["dojo/_base/declare",
        "dijit/_WidgetBase",
        "dijit/_TemplatedMixin",
        "dijit/_WidgetsInTemplateMixin",
        "dojo/text!./createTestCase.html",
        "dijit/form/Button",
        "dojo/_base/lang", 
        "dojox/grid/EnhancedGrid", 
        "dojo/data/ItemFileWriteStore",
        "dojo/dom",
        "dojox/grid/enhanced/plugins/IndirectSelection",
        "dijit/form/CheckBox",
        "dojo/dom-construct",
        "keyWordsDriven/TestCase/testCaseAddingDialog"
        ],
        function(declare,
        		WidgetBase,
        		TemplatedMixin,
        		WidgetsInTemplateMixin,
        		template,
        		Button,
        		lang, EnhancedGrid, ItemFileWriteStore, dom,
        		IndirectSelection,CheckBox,domConstruct,
        		testCaseAddingDialog
        		
        ){
	
	return declare("keyWordsDriven.TestCase.createTestCase",[WidgetBase, TemplatedMixin, WidgetsInTemplateMixin],{
		
		templateString : template,
	
	postCreate: function postCreate() {
		
		
		  var data = {
			      identifier: "CaseID",
			      items: [
			      		{
			      			"CaseSuite": "Shopping",
			      			"CaseID":"confirmGoods_001",
			      			"keyWords": "确认购买的商品信息",
			      			"FunctionDescription": "confirmGoodsInfo",
			      			"apiParameters":"('myBook','5','child')"
			      		},
		      			{
			      			"CaseSuite": "Shopping",
			      			"CaseID":"shoppingCart_001",
			      			"keyWords": "添加商品到购物车",
			      			"FunctionDescription": "AddToShoppingCarts",
			      			"apiParameters":"('myBook','5','child')"
			      			
		      			},
		      			{
			      			"CaseSuite": "Shopping",
			      			"CaseID":"shoppingCart_002",
			      			"keyWords": "打开购物车",
			      			"FunctionDescription": "OpenShoppCarts",
			      			"apiParameters":"('myBook','5','child')"
			      			
		      			},
		      			{
			      			"CaseSuite": "Shopping",
			      			"CaseID":"shoppingCart_003",
			      			"keyWords": "确认购物车信息",
			      			"FunctionDescription": "confirmGoodsInCarts",
			      			"apiParameters":"('myBook','5','child')"
			      			
		      			},
		      			{
			      			"CaseSuite": "Shopping",
			      			"CaseID":"confirmAddress_001",
			      			"keyWords": "确认/修改收货地址",
			      			"FunctionDescription": "manageAddress",
			      			"apiParameters":"('myBook','5','child')"
			      			
		      			},
		      			{
			      			"CaseSuite": "Shopping",
			      			"CaseID":"payment_001",
			      			"keyWords": "最终支付",
			      			"FunctionDescription": "finalPayment",
			      			"apiParameters":"('myBook','5','child')"
			      			
		      			},
		      		 ]
			    };
			    
		  
			    var store = new ItemFileWriteStore({data: data});

			    /*set up layout*/
			    var layout = [
					{ name: "Case Suite", field: "CaseSuite", width: "150px" },
					{ name: "Case Id", field: "CaseID", width: "150px" },
					{ name: "Key Words", field: "keyWords", width: "250px" },
					{ name: "Function Name", field: "FunctionDescription", width: "150px" },
					{ name: "API Parameters",field : "apiParameters",width: "200px"}
			    ];

			    /*create a new grid*/
			    var grid = new EnhancedGrid({
			        id: 'grid',
			        store: store,
			        structure: layout,
			        rowSelector: '20px',
			        plugins: {indirectSelection: {headerSelector:true, width:"40px", styles:"text-align: center;"}}}, 
			        document.createElement('div')
			    );

			        /*append the new grid to the div*/
			    dojo.byId("gridDiv").appendChild(grid.domNode);			    
			    
			        /*Call startup() to render the grid*/
			    grid.startup();
			    var refreshButton = new Button({
			        label: "Refresh",
			        onClick: function(){
			           
			        }
			    }, document.createElement('div'));
			    refreshButton.placeAt(dojo.byId('btn'));
			    
			    var caseAddingDialog = new testCaseAddingDialog();
			    var addButton = new Button({
			        label: "Add New Test Case",
			        onClick: function(){
			        	caseAddingDialog._dialog.show();
			        }
			    }, document.createElement('div'));
			    
			    domConstruct.place(addButton.domNode,refreshButton.domNode,"after");
			 
			    var execButton = new Button({
			        label: "Start to Run Test Bucket",
			        onClick: function(){    	
			        	
			        }
			    }, document.createElement('div'));
			    domConstruct.place(execButton.domNode, addButton.domNode, "after");
			    

		}
	 		
})
})

	