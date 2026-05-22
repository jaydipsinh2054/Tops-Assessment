function LinkList({ links, removeLink }) {

  return (

    <div className="link-list">

      {links.map((link, index) => (

        <div
          key={index}
          className="link-card"
        >

          <a
            href={link.url}
            target="_blank"
            rel="noreferrer"
          >

            <button className="link-btn">
              {link.title}
            </button>

          </a>

          <button
            className="remove-btn"
            onClick={() =>
              removeLink(index)
            }
          >
            Remove
          </button>

        </div>

      ))}

    </div>

  );

}

export default LinkList;