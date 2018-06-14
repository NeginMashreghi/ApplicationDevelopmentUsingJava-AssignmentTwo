# Servlet Assignment 2

## Problem statement:

Having submitted and reviewed your first assignment, your manager is presently surprised in your capabilities. The management team has taken note to your strong skill-set and have overwhelmingly received excellent feedback directly from the clients on the first phase of the application.

Based on the review of your first assignment however, the clients have now come back to your managers and business analysts (BA) with even more requirements. These new requirements have been gathered by your BA’s and after a number of meetings, have been conveniently tabulated into an updated business requirements document. The summary of these new requirements have been captured in the wireframes and pages below.

Your manager continues to be under direct pressure from the company Director to produce the final product that essentially encompasses all client requirements. Your manager, fully recognizes your potential, has much more confidence in you and is now allowing you to lead the remaining portion of this project to project completion and delivery.

<img width="1020" alt="screen shot 2018-06-14 at 4 16 37 pm" src="https://user-images.githubusercontent.com/27638465/41436020-81c2d782-6fee-11e8-9a37-052203c5a535.png">

## Department

All organizations have specialized areas within them that handle specific verticals of the company’s work, called Departments. Departments such as accounting, Marketing, Planning, Application Development, IT, Support etc.… exist to house and oversee related and associated tasks. Your application must allow for the entry of the different departments within your company’s growing organization.

**View Departments Page**

The Business Analysts have provided a wireframe mockup of a potential view department page below:

<img width="589" alt="screen shot 2018-06-14 at 4 13 11 pm" src="https://user-images.githubusercontent.com/27638465/41436070-a18554be-6fee-11e8-9685-c3b254cece6f.png">

The Departments page, when called, simply displays all known company departments when the page is first rendered. The wireframe above merely shows an example of the possible departments that may have been entered via the departments entry page


## Employee

Companies are built by/on/with employees. The application you are required to build must also allow for the entry of employee profiles. The new wireframes added, conceptualize the new View Employee Page.

**View Employee Page**

The Business Analysts have provided a wireframe mockup of a view employee listing page below:

<img width="612" alt="screen shot 2018-06-14 at 4 13 24 pm" src="https://user-images.githubusercontent.com/27638465/41436086-ab69c230-6fee-11e8-9a9f-f5995fb668c9.png">


The View Employee/Employee Listing page when initially rendered, shows an empty result set (ie. no employees), however after a department is selected via the dropdown, and a search is executed, the expected result is that the entire result set of employees, within that selected department is displayed. Please refer to the next diagram for a hypothetical example of a departmental employee listing.



## Group
Departments can be further divided into groups. A group can be thought synonymous to a team. A department can have many groups, your application must allow for the entry and configuration of a group.
The new wireframes added, conceptualize the new View Group Page.

**Group View Page**

The Business Analysts have provided a wireframe mockup of a potential view group page below

<img width="616" alt="screen shot 2018-06-14 at 4 13 40 pm" src="https://user-images.githubusercontent.com/27638465/41436114-b86b417a-6fee-11e8-811c-775867c390ae.png">

The view group page is used to display all groups within a department. This page, when initially rendered, shows an empty result set (ie. no groups), however after a department is selected via the dropdown, the group combo box becomes enabled and the selection of groups associated with that department become available. Finally, once a search is performed, with department and group selected, a result set is rendered showing the entire list of employees associated with that group. An example wireframe, showing a hypothetical query for the “Super Heroes” group is displayed in the following diagram.


## Reports

The new wireframes added, conceptualize the new Create Report Template, Enter Report and View Report Pages. To help clarify these requirements, examples of each of these new pages, with sample entry data has been provided.

**Create Report Template - Example**

The Business Analysts have provided a wireframe mockup of a potential create report template page below
Figure 6:

<img width="570" alt="screen shot 2018-06-14 at 4 13 59 pm" src="https://user-images.githubusercontent.com/27638465/41436127-bf756d2e-6fee-11e8-86d5-7900a7c6566c.png">

The create report template page is used to create a report template, that can later be selected to generate a report. The create report template page, has a “Details” area and three sections (Sections I-III).
The details section contains three mandatory fields, namely, a “Report Template” text field, an unmodifiable date field (automatically populated with current date), and a department combo dropdown that is populated with the list of known departments.
The remaining sections (Sections I-III) provide a text area each, where the user can enter a name to name each section uniquely. Section I specifically, allows for only 5 criteria’s (text fields) to be entered at a maximum. A criterion is simply an area where the user to enter in a text criterion used for evaluation. Section II-III on the other hand, allow for only three criteria each, and again at a maximum. A minimum of one criteria much be entered in each Section (I-III) in order for the report template to be successfully created.
Lastly, each criterion is attributed a maximum weight of between 1-5.
Once a report template is created successfully, the template becomes available for selection when entering a report.


## Enter Group Report – Example

The Business Analysts have provided a wireframe mockup of a potential enter report page, for an example group, has been provided below.

<img width="599" alt="screen shot 2018-06-14 at 4 14 22 pm" src="https://user-images.githubusercontent.com/27638465/41436133-c4d965c2-6fee-11e8-9f21-711f8295275a.png">


## Attendance

The Business Analysts have identified the need for an Attendance section, a simple and convenient way to tracks employee attendance, on a department-by-department basis. The section below further depicts what the business is thinking of, as usual wireframe mockups have been provided to help clarify and conceptualize some of the requirements desirables.

**Enter Attendance**
The Business Analysts have provided a wireframe mockup of a potential enter attendance page below

<img width="592" alt="screen shot 2018-06-14 at 4 15 13 pm" src="https://user-images.githubusercontent.com/27638465/41436145-caf40c6e-6fee-11e8-9809-58c20dcf9494.png">

The Enter Attendance page, when initially rendered, shows an empty result set (ie. no employees), however after a department is selected via the dropdown, and a search is executed, the expected result is that the entire result set of employees, within that selected department is displayed. To enter an attendance report, the user selects the date to be recorded (via date chooser box), then simply checks the user as present. Once the date is entered, the attendance data is committed for the configured date.

