function isArray(val) {
	return val &&
		!(val.propertyIsEnumerable('length')) &&
		typeof val === 'object' &&
		typeof val.length === 'number';
}
function createEmployeeOptions(employees, selectedId) {
	var length = employees.length;
	var arr = [];
	for (var i = 0; i < length; i++) {
		var opt = $("<option />")
				.attr("value", employees[i].id)
				.text(employees[i].firstName + " " + employees[i].lastName);
		if (selectedId == employees[i].id)
			opt.attr("selected", "true");
		arr.push(opt);
	}
	return arr;
}
function cloneArr(arr1) {
	var length = arr1.length;
	var arr2 = [];
	for (var i = 0; i < length; i++) {
		arr2.push(arr1[i].clone());
	}
	return arr2;
}

function get(url, progress, content, callback, errorCallback) {
	var showProgressTimer = setTimeout(function() {
		content.fadeOut();
		progress.fadeIn();
	}, 500);
	// TODO: XMLWebRequest
	$.getJSON(
		url,
		function(data) {
			clearTimeout(showProgressTimer);
			progress.fadeOut();
			if (!isArray(data)) {
				errorCallback();
			} else {
				callback(data);
			}
		}
	).fail(function (jqxhr, textStatus, error) {
		console.log(jqxhr);
		console.log(textStatus);
		console.log(error);
	});
}

function getEmployeesByDepartment(departmentId, progress, content, callback, errorCallback) {
	return get("/servlet_assignment_2/api/employees/by/department/" + departmentId, progress, content, callback, errorCallback);
}

function getAttendanceByEmployee(employeeId, date, progress, content, callback, errorCallback) {
	return get("/servlet_assignment_2/api/attendance/by/employee/" + employeeId + "/" + epoch, progress, content, callback, errorCallback);
}
