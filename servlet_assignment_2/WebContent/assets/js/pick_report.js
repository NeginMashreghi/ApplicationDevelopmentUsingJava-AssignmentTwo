function doTheDo() {
	var value = $("#departmentSelect").val();
	if (value == -1) return;
	var url = "/servlet_assignment_2/reports/new.jsp?template_id=" + value;
	window.location.href = url;
}