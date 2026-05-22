import { useState } from "react";

import { validateURL } from "../utils/validation";

function LinkForm({ addLink }) {

  const [title, setTitle] = useState("");
  const [url, setURL] = useState("");
  const [error, setError] = useState("");

  function handleSubmit(e) {

    e.preventDefault();

    if (!title || !url) {

      setError("All fields are required");

      return;

    }

    if (!validateURL(url)) {

      setError(
        "URL must start with https://"
      );

      return;

    }

    setError("");

    addLink({
      title,
      url
    });

    setTitle("");
    setURL("");

  }

  return (

    <div>

      <form onSubmit={handleSubmit}>

        <input
          type="text"
          placeholder="Enter Title"
          value={title}
          onChange={(e) =>
            setTitle(e.target.value)
          }
        />

        <input
          type="text"
          placeholder="https://example.com"
          value={url}
          onChange={(e) =>
            setURL(e.target.value)
          }
        />

        <button type="submit">
          Add Link
        </button>

      </form>

      <p className="error">
        {error}
      </p>

    </div>

  );

}

export default LinkForm;