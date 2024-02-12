import "./InputPrompt.css";
function InputPrompt() {
  return (
    <form className="input">
      <input
        type="input"
        placeholder=" / Write command here"
        className="input_box"
      ></input>
    </form>
  );
}

export default InputPrompt;
