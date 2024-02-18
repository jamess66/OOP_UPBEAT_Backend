import "./InputPrompt.css";
import React from "react";
interface Props {
  showdi: String;
  setshowdi: React.Dispatch<React.SetStateAction<String>>;
}
const InputPrompt = () => {
  return (
    <form className="input">
      <textarea
        // type="input"
        placeholder=" / Write command here"
        className="input_box"
      ></textarea>
    </form>
  );
};

export default InputPrompt;
