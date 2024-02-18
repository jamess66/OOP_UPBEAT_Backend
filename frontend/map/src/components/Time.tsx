import "./Time.css";

function Time() {
  return (
    <div
      style={{
        marginLeft: "10px",
      }}
    >
      <span className="count-timer">
        {"minute"}:{"second"}
      </span>
    </div>
  );
}

export default Time;
