const calendarTitle = document.getElementById("calendarTitle");
const calendarBody = document.getElementById("calendarBody");
const prevBtn = document.getElementById("prevBtn");
const nextBtn = document.getElementById("nextBtn");
const languageSelect = document.getElementById("languageSelect");
const monthSelect = document.getElementById("monthSelect");
const yearSelect = document.getElementById("yearSelect");

const labels = {
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
    language: "Language:",
    jump: "Jump To:",
  },
  ms: {
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
    days: ["Ahd", "Isn", "Sel", "Rab", "Kha", "Jum", "Sab"],
    language: "Bahasa:",
    jump: "Lompat Ke:",
  },
};

// Current calendar view state.
let selectedMonth = 4;
let selectedYear = 2026;
let selectedLanguage = "en";

// Populate month and year dropdown options based on active language and allowed range.
function fillSelectors() {
  monthSelect.innerHTML = "";
  labels[selectedLanguage].months.forEach((month, index) => {
    const option = document.createElement("option");
    option.value = index;
    option.textContent = month;
    monthSelect.appendChild(option);
  });

  yearSelect.innerHTML = "";
  for (let year = 1950; year <= 2035; year++) {
    const option = document.createElement("option");
    option.value = year;
    option.textContent = year;
    yearSelect.appendChild(option);
  }
}

// Refresh static UI text when language changes.
function updateStaticText() {
  document.querySelector("label[for='languageSelect']").textContent =
    labels[selectedLanguage].language;
  document.querySelector("label[for='monthSelect']").textContent =
    labels[selectedLanguage].jump;

  document.querySelectorAll("th").forEach((heading, index) => {
    heading.textContent = labels[selectedLanguage].days[index];
  });
}

// Build and display the calendar grid for the selected month and year.
function renderCalendar() {
  const firstDay = new Date(selectedYear, selectedMonth, 1).getDay();
  const daysInMonth = new Date(selectedYear, selectedMonth + 1, 0).getDate();
  const today = new Date();

  calendarTitle.textContent = `${labels[selectedLanguage].months[selectedMonth]} ${selectedYear}`;
  monthSelect.value = selectedMonth;
  yearSelect.value = selectedYear;
  calendarBody.innerHTML = "";

  let dateNumber = 1;

  for (let row = 0; row < 6; row++) {
    const tr = document.createElement("tr");

    for (let col = 0; col < 7; col++) {
      const td = document.createElement("td");

      if ((row === 0 && col < firstDay) || dateNumber > daysInMonth) {
        td.className = "empty";
        td.textContent = "";
      } else {
        td.textContent = dateNumber;

        if (col === 0) {
          td.classList.add("sunday");
        }

        if (col === 5) {
          td.classList.add("friday");
        }

        if (
          dateNumber === today.getDate() &&
          selectedMonth === today.getMonth() &&
          selectedYear === today.getFullYear()
        ) {
          td.classList.add("today");
        }

        dateNumber++;
      }

      tr.appendChild(td);
    }

    calendarBody.appendChild(tr);

    if (dateNumber > daysInMonth) {
      break;
    }
  }
}

// Move calendar by month while keeping year inside allowed boundaries.
function changeMonth(amount) {
  selectedMonth += amount;

  if (selectedMonth < 0) {
    selectedMonth = 11;
    selectedYear--;
  }

  if (selectedMonth > 11) {
    selectedMonth = 0;
    selectedYear++;
  }

  // set min year to 1950
  if (selectedYear < 1950) {
    selectedYear = 1950;
    selectedMonth = 0;
  }

  // set max year to 2035
  if (selectedYear > 2035) {
    selectedYear = 2035;
    selectedMonth = 11;
  }

  renderCalendar();
}

// Hook up navigation button actions.
prevBtn.addEventListener("click", () => changeMonth(-1));
nextBtn.addEventListener("click", () => changeMonth(1));

// Re-render when user picks a specific month.
monthSelect.addEventListener("change", () => {
  selectedMonth = Number(monthSelect.value);
  renderCalendar();
});

// Re-render when user picks a specific year.
yearSelect.addEventListener("change", () => {
  selectedYear = Number(yearSelect.value);
  renderCalendar();
});

// Rebuild labels/selectors and re-render when language changes.
languageSelect.addEventListener("change", () => {
  selectedLanguage = languageSelect.value;
  fillSelectors();
  updateStaticText();
  renderCalendar();
});

// Initial page setup.
fillSelectors();
updateStaticText();
renderCalendar();
