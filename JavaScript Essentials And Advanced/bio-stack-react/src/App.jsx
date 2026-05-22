import { useEffect, useState } from "react";

import LinkForm from "./components/LinkForm";
import LinkList from "./components/LinkList";
import ThemeToggle from "./components/ThemeToggle";

import {
  getLinks,
  getTheme,
  saveLinks,
  saveTheme
} from "./utils/storage";

function App() {

  /* STATE */

  const [links, setLinks] = useState(
    getLinks()
  );

  const [darkMode, setDarkMode] =
    useState(
      getTheme() === "dark"
    );

  /* SAVE LINKS */

  useEffect(() => {

    saveLinks(links);

  }, [links]);

  /* SAVE THEME */

  useEffect(() => {

    saveTheme(
      darkMode ? "dark" : "light"
    );

  }, [darkMode]);

  /* ADD LINK */

  function addLink(newLink) {

    setLinks([
      ...links,
      newLink
    ]);

  }

  /* REMOVE LINK */

  function removeLink(index) {

    const updatedLinks =
      links.filter(
        (_, i) => i !== index
      );

    setLinks(updatedLinks);

  }

  return (

    <div className={
      darkMode
        ? "app dark"
        : "app"
    }>

      <div className="container">

        <h1>
          Creator Bio-Stack
        </h1>

        <ThemeToggle
          darkMode={darkMode}
          setDarkMode={setDarkMode}
        />

        <LinkForm addLink={addLink} />

        <h2>
          Profile Preview
        </h2>

        <LinkList
          links={links}
          removeLink={removeLink}
        />

      </div>

    </div>

  );

}

export default App;