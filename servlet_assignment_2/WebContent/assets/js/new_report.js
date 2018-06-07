var selector = "input[name='report_type']";
var groupTypeSelect = $("select[name='group_type']");
var employeeTypeSelect = $("select[name='employee_type']");

var radioButtons = $(selector);
radioButtons.change(
	function(event) {
		var disabled = "disabled";
		var selected = $(selector + ":checked").val();

		if (selected == 1) {
			// Group
			groupTypeSelect.prop(disabled, false);
			employeeTypeSelect.prop(disabled, true);
		} else {
			// Employee

			groupTypeSelect.prop(disabled, true);
			employeeTypeSelect.prop(disabled, false);
		}
	}	
);

$('#datePreview')[0].valueAsDate = new Date();