const STORAGE_KEY = "bioLinks";

export function saveLinks(links) {

  localStorage.setItem(
    STORAGE_KEY,
    JSON.stringify(links)
  );

}

export function getLinks() {

  return JSON.parse(
    localStorage.getItem(STORAGE_KEY)
  ) || [];

}

/* THEME */

export function saveTheme(theme) {

  localStorage.setItem("theme", theme);

}

export function getTheme() {

  return localStorage.getItem("theme");

}