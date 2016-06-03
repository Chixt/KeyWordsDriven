
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
	
	Parser.parse();
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
	this._dialog.hide();
	
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