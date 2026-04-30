"use strict";

// Get today's date to highlight the current day in the calendar
const TODAY = new Date();

// Track the current month and year being displayed
let currentMonth = TODAY.getMonth();
let currentYear = TODAY.getFullYear();

// DOM element references for the calendar UI
let selectYear;
let selectMonth;
let monthAndYear;

// Arrays to store month and day names based on the selected language
let months = [];
let days = [];

// Track the currently selected language (en or bm)
let currentLanguage = "en";

/**
 * Locale data for all supported languages
 * Each language has its month names and day abbreviations
 */
const LOCALE_CALENDAR = {
  en: {
    months: [
      "January",
      "February",
      "March",
      "April",
      "May",
      "June",
      "July",
      "August",
      "September",
      "October",
      "November",
      "December",
    ],
    days: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"],
  },
  bm: {
    months: [
      "Januari",
      "Februari",
      "Mac",
      "April",
      "Mei",
      "Jun",
      "Julai",
      "Ogos",
      "September",
      "Oktober",
      "November",
      "Disember",
    ],
    days: ["Ahad", "Isnin", "Selasa", "Rabu", "Khamis", "Jumaat", "Sabtu"],
  },
};

/**
 * Generate year options for the year dropdown
 * Creates option elements from start year to end year
 * @param {number} start - Starting year
 * @param {number} end - Ending year
 * @returns {DocumentFragment} Fragment containing all year options
 */
function generateYearOptions(start, end) {
  const fragment = document.createDocumentFragment();

  for (let year = start; year <= end; year++) {
    const option = document.createElement("option");
    option.value = String(year);
    option.textContent = String(year);
    fragment.appendChild(option);
  }

  return fragment;
}

/**
 * Initialize the calendar when the DOM is fully loaded
 * Sets up event listeners, populates dropdowns, and renders the calendar
 */
function initializeCalendar() {
  // Get references to all required DOM elements
  selectYear = document.getElementById("year");
  selectMonth = document.getElementById("month");
  monthAndYear = document.getElementById("monthAndYear");
  const previousButton = document.getElementById("previous");
  const nextButton = document.getElementById("next");
  const languageSelect = document.getElementById("language");

  // Exit early if any required element is missing
  if (
    !selectYear ||
    !selectMonth ||
    !monthAndYear ||
    !previousButton ||
    !nextButton ||
    !languageSelect
  ) {
    return;
  }

  // Attach click and change event listeners to interactive elements
  previousButton.addEventListener("click", previous);
  nextButton.addEventListener("click", next);
  selectMonth.addEventListener("change", jump);
  selectYear.addEventListener("change", jump);
  languageSelect.addEventListener("change", changeLanguage);

  // Populate year dropdown with years from 1970 to 2050
  selectYear.replaceChildren(generateYearOptions(1970, 2050));

  // Get the initial language from the calendar's data-lang attribute
  const calendar = document.getElementById("calendar");
  currentLanguage = calendar?.getAttribute("data-lang") ?? "en";
  languageSelect.value = currentLanguage;

  // Get locale data for the current language
  const localeData = LOCALE_CALENDAR[currentLanguage] ?? LOCALE_CALENDAR.en;

  // Set global month and day names arrays
  months = localeData.months;
  days = localeData.days;

  // Render the calendar headers and populate month options
  renderHeaderDays();
  populateMonthOptions();

  // Display the current month and year
  showCalendar(currentMonth, currentYear);
}

/**
 * Render the calendar header row with day names
 * Creates <th> elements for each day of the week in the current language
 */
function renderHeaderDays() {
  const headerRow = document.createElement("tr");

  // Create a header cell for each day of the week
  for (const day of days) {
    const th = document.createElement("th");
    th.dataset.days = day;
    th.textContent = day;
    headerRow.appendChild(th);
  }

  // Replace the old header with the new one
  const theadMonth = document.getElementById("thead-month");
  if (theadMonth) {
    theadMonth.replaceChildren(headerRow);
  }
}

/**
 * Populate the month dropdown with month options in the current language
 * Clears existing options and creates new ones based on the 'months' array
 */
function populateMonthOptions() {
  // Clear all existing options
  selectMonth.replaceChildren();

  // Create an option for each month in the current language
  months.forEach((month, index) => {
    const option = document.createElement("option");
    option.value = String(index);
    option.textContent = month;
    selectMonth.appendChild(option);
  });
}

/**
 * Handle language change event
 * Updates the calendar display (headers and month names) when user changes language
 */
function changeLanguage() {
  const languageSelect = document.getElementById("language");
  if (!languageSelect) return;

  // Get the newly selected language
  currentLanguage = languageSelect.value;

  // Get the locale data for the selected language
  const localeData = LOCALE_CALENDAR[currentLanguage] ?? LOCALE_CALENDAR.en;

  // Update month and day names arrays with the new language
  months = localeData.months;
  days = localeData.days;

  // Re-render the calendar with the new language
  renderHeaderDays();
  populateMonthOptions();
  showCalendar(currentMonth, currentYear);
}

/**
 * Call initializeCalendar when the DOM is ready
 * Uses DOMContentLoaded for early-loading scripts, or immediate call if already loaded
 */
if (document.readyState === "loading") {
  document.addEventListener("DOMContentLoaded", initializeCalendar);
} else {
  initializeCalendar();
}

/**
 * Show the next month
 * Increments the month and year, handles year rollover (Dec -> Jan)
 */
function next() {
  currentYear = currentMonth === 11 ? currentYear + 1 : currentYear;
  currentMonth = (currentMonth + 1) % 12;
  showCalendar(currentMonth, currentYear);
}

/**
 * Show the previous month
 * Decrements the month and year, handles year rollover (Jan -> Dec)
 */
function previous() {
  currentYear = currentMonth === 0 ? currentYear - 1 : currentYear;
  currentMonth = currentMonth === 0 ? 11 : currentMonth - 1;
  showCalendar(currentMonth, currentYear);
}

/**
 * Jump to a specific month and year selected from the dropdowns
 * Called when user changes the month or year selector
 */
function jump() {
  currentYear = Number.parseInt(selectYear.value, 10);
  currentMonth = Number.parseInt(selectMonth.value, 10);
  showCalendar(currentMonth, currentYear);
}

/**
 * Render the calendar grid for a specific month and year
 * Creates table rows and cells for all days in the month
 * @param {number} month - Month index (0-11)
 * @param {number} year - Full year (e.g., 2026)
 */
function showCalendar(month, year) {
  // Get the day of the week the month starts on (0=Sunday, 6=Saturday)
  const firstDay = new Date(year, month).getDay();
  const tableBody = document.getElementById("calendar-body");

  if (!tableBody) {
    return;
  }

  // Calculate how many days are in this month
  const totalDays = daysInMonth(month, year);

  // Clear the table body to prepare for new content
  tableBody.textContent = "";

  // Update the month and year heading
  monthAndYear.textContent = `${months[month]} ${year}`;

  // Ensure the dropdowns reflect the current month and year
  selectYear.value = String(year);
  selectMonth.value = String(month);

  // Generate calendar rows (6 rows max, representing up to 6 weeks)
  let date = 1;
  for (let i = 0; i < 6; i++) {
    const row = document.createElement("tr");
    let hasDateInRow = false;

    // Generate cells for each day of the week (Sunday to Saturday)
    for (let j = 0; j < 7; j++) {
      // Skip empty cells at the beginning of the month
      if (i === 0 && j < firstDay) {
        row.appendChild(document.createElement("td"));
        continue;
      }

      // Stop if we've gone through all days in the month
      if (date > totalDays) {
        break;
      }

      // Create a cell for this date
      const cell = document.createElement("td");
      cell.dataset.date = String(date);
      cell.dataset.month = String(month + 1);
      cell.dataset.year = String(year);
      cell.dataset.monthName = months[month];
      cell.classList.add("date-picker");

      // Add special classes for Sunday and Friday
      if (j === 0) {
        cell.classList.add("sunday");
      }
      if (j === 5) {
        cell.classList.add("friday");
      }

      // Add the date number to the cell
      const dayLabel = document.createElement("span");
      dayLabel.textContent = String(date);
      cell.appendChild(dayLabel);

      // Highlight today's date with the 'selected' class
      if (
        date === TODAY.getDate() &&
        year === TODAY.getFullYear() &&
        month === TODAY.getMonth()
      ) {
        cell.classList.add("selected");
      }

      row.appendChild(cell);
      hasDateInRow = true;
      date++;
    }

    // Stop adding rows if this row has no dates and we've exhausted all days
    if (!hasDateInRow && date > totalDays) {
      break;
    }

    tableBody.appendChild(row);

    // Stop if we've added all the days in the month
    if (date > totalDays) {
      break;
    }
  }
}

/**
 * Calculate the number of days in a given month
 * Uses a clever trick: subtracting day 32 gives us the number of days in the month
 * @param {number} month - Month index (0-11)
 * @param {number} year - Full year (e.g., 2026)
 * @returns {number} Number of days in the month
 */
function daysInMonth(month, year) {
  return 32 - new Date(year, month, 32).getDate();
}
