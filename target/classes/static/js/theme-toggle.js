const THEME_KEY = "theme";

/* Apply saved theme when page loads */
document.addEventListener("DOMContentLoaded", () => {
    const savedTheme = localStorage.getItem(THEME_KEY) || "light";
    document.documentElement.setAttribute("data-theme", savedTheme);
    updateThemeIcon(savedTheme);
});

/* Toggle dark / light mode */
function toggleTheme() {
    const currentTheme =
        document.documentElement.getAttribute("data-theme") || "light";

    const newTheme = currentTheme === "dark" ? "light" : "dark";

    document.documentElement.setAttribute("data-theme", newTheme);
    localStorage.setItem(THEME_KEY, newTheme);
    updateThemeIcon(newTheme);
}

/* Update icon on navbar button */
function updateThemeIcon(theme) {
    const btn = document.querySelector(".theme-toggle");
    if (!btn) return;

    btn.textContent = theme === "dark" ? "☀️" : "🌙";
}
