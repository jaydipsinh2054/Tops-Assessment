function ThemeToggle({ darkMode, setDarkMode }) {

  function toggleTheme() {

    setDarkMode(!darkMode);

  }

  return (

    <button
      className="theme-btn"
      onClick={toggleTheme}
    >
      {darkMode ? "Light Mode" : "Dark Mode"}
    </button>

  );

}

export default ThemeToggle;