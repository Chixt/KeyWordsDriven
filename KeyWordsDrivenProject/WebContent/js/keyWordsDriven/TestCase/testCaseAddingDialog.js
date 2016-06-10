
/**
 * testCaseEditDialog
 * 
 * 
 * 
 */
define([ "dojo/_base/declare",
         "dojo/query",
         "dojo/dom-construct",
         "dijit/registry",
     	 "dojo/aspect",
     	 "dojo/dom-class",
     	 "dijit/Dialog",
		"dijit/_WidgetBase",
		"dijit/_TemplatedMixin",
		"dijit/_WidgetsInTemplateMixin",
		"dojo/text!./testCaseAddingDialog.html",
		"dojo/dom-style",
		"dojo/_base/lang",
		"dojo/parser"
		],
	function(declare,
			query,
			domConstruct,
			registry,
			aspect,
			domClass,
			Dialog,
		WidgetBase,
		TemplatedMixin,
		WidgetsInTemplateMixin,
		template,
		domStyle,
		lang,
		Parser
	){

var TestCaseAddingDialog = declare("keyWordsDriven.TestCase.testCaseAddingDialog", 
	[ WidgetBase, TemplatedMixin, WidgetsInTemplateMixin ], {
	
templateString : template,


/**
 * postCreate
 * 
 * <pre>
 * - Build new dialog
 * - Connect button events
 * - Add template to dialog.
 * - show dialog.
 *</pre>
 */
postCreate : function() {
	

	// Build dialog object
	this._dialog = new Dialog( {
		title : "Test Case Adding Dialog"
	});

	
	// Connect cancel button to close of dialog.
	this.connect(this.cancelButton, "onClick", this.cancelClicked);
	// Connect OK button to OKClicked() function
	this.connect(this.OKButton, "onClick", this.OKClicked);

	this._dialog.set("content", this.domNode);

},




/**
 * resize
 * 
 * resize the bordercontainer
 */
resize: function(size) {
	this.inherited(arguments);
//	this.caseAddingLayout.resize(size);
},

destroy:function(){
	if(this._dialog){
		this._dialog.destroyRecursive();
	}
},
/**
 * OKClicked
 * 
 * Someone has clicked 'OK'. Call onComplete() and parent will know
 * to continue with the desired action
 * 
 */
OKClicked : function() {
	
	 var context = this;
	
	dojo.ready(this.sendText(context));
	
	this._dialog.hide();
	
},


sendText: function (context){

	    // The parameters to pass to xhrPost, the message, and the url to send it to
	    // Also, how to handle the return and callbacks.
	    var xhrArgs = {
	      url: "/keyWordsDriven/newcase/Add",
	      postData: dojo.toJson(
	      {
	    	      "caseSuiteID": context.caseSuiteInput.value,
	    	      "keyWordsDescription": context.keywordsInput.value,
	    	      "apiFunction": context.functionNameInput.value,
	    		  "apiParameters": context.apiParametersInput.value
	      }),
	      handleAs: "text",
	      headers: { "Content-Type": "application/json" },
	      load: function(data){
	       alert('success~' + data);
	      },
	      error: function(error){
    	  alert('failed!'+ error);
	      }
	    }
	    // Call the asynchronous xhrPost
	    var deferred = dojo.xhrPost(xhrArgs);
	 
	},

/**
 * cancelClicked.
 * 
 * When the cancel button is clicked, close dialog. Previous screen will
 * still be there and nothing will happen.
 * 
 */
cancelClicked : function() {
	this._dialog.hide();
	
}
});

return TestCaseAddingDialog;
});	