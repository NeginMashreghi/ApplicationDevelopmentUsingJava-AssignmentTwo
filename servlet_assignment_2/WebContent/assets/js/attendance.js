var departmentSelect = $("#departmentSelect");
var dateInput = $("#dateInput");
var attendanceBody = $("#attendanceBody");

var progress = $("#progress");
var error = $("#error");

var step2 = $("#step2");

function enterAttendanceClicked() {
	step2.fadeOut();

	var department = departmentSelect.val();
	var date = dateInput.val();

	getEmployeesByDepartment(department, progress, step2,
		// Success
		function(data) {
			if (data.length == 0) {
				step2.fadeOut();
				error.fadeIn();
			}
		},
		// Error
		function() {
			
		}
	);
}

// Set the default date to today
dateInput[0].valueAsDate = new Date();