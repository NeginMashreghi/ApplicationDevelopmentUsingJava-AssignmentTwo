var departmentSelect = $("#departmentSelect");
var createGroup = $("#createGroup");
var error = $("#error");
var progress = $("#progress");

function hideAll() {
	createGroup.fadeOut();
	error.fadeOut();
	progress.fadeOut();
}
function getEmployeeData(value, callback) {
	var showProgressTimer = setTimeout(function() {
		createGroup.fadeOut();
		progress.fadeIn();
	}, 500);
	$.getJSON(
		"/servlet_assignment_2/api/employees/by/department/" + value,
		function (data) {
			// Clear the timeout so the progress bar doesn't show
			clearTimeout(showProgressTimer);
			// Hide the progress bar in-case its already showing
			progress.hide();

			if (!isArray(data) || data.length == 0) {
				createGroup.fadeOut();
				error.fadeIn();
			} else {
				callback(data);
			}
		}
	);
}
function getPreviousIndex(key) {
	return prevEmployees.hasOwnProperty(key) ? parseInt(prevEmployees[key]) : -1;
}
function departmentChanged(event) {
	var value = departmentSelect.val();
	if (value < 0) {
		hideAll();
	} else {
		getEmployeesByDepartment(value, progress, createGroup,
			// Success
			function(data) {
				$("input[name=department_id]").attr("value", value);
				for (var i = 1; i < 7; i++) {
					var key = "employee" + String(i);
					// For sticky forms
					var selectedIndex = getPreviousIndex(key);
					var arr = createEmployeeOptions(data, selectedIndex);
					var select = $("select[name=" + key + "]");

					select.children('option:not(:first)').remove();
					for (var i2 = 0; i2 < arr.length; i2++)
						select.append(arr[i2]);
				}
				error.fadeOut();
				createGroup.fadeIn();
			},
			// Error
			function() {
				error.fadeIn();
				createGroup.fadeOut();
			}
		);
	}
}
departmentSelect.change(departmentChanged);
if (prevDepartment != -1) {
	departmentSelect.val(prevDepartment);
	departmentSelect.trigger("change");
}
