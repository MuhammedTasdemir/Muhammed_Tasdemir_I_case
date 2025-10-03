This project automates the QA careers flow on the Insider website using Selenium WebDriver, Java, and TestNG. The tests follow the Page Object Model (POM) design pattern, making them modular, maintainable, and easy to extend. It automates the following steps: open homepage, accept cookies, navigate to the Careers page, filter QA jobs by location and department, hover over job cards, click “View Role,” and verify redirection to Lever’s application page.

Test Flow:
- Open Insider homepage.
- Accept cookies automatically if present.
- Navigate to the Careers page via the menu.
- Verify Locations, Teams, and Life at Insider blocks are visible.
- Open the QA Jobs page, click “See All QA Jobs,” and filter by "Istanbul, Turkiye" and "Quality Assurance".
- Scroll to job cards, hover, and click “View Role.”
- Switch to the new tab and verify redirection to the Lever application page.
