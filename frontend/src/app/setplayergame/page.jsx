import Link from "next/link";
import "../styles/setplayergame.css";
function playergame() {
  return (
    <header className="background">
      <div className="boderuser">
        <div className="component-setplayergame-front">
          USERNAME
          <form>
            <input
              className="textarea-styles"
              type="text"
              placeholder=" / Write your name "
            />
          </form>
          <div className="component-setplayergame-fronts mt-4">
            CODE
            <form>
              <input
                className="textarea-styles"
                type="text"
                placeholder=" /code "
              />
            </form>
          </div>
          <div>
            <Link href="/">
              <button className="buttonjoin">JOIN GAME</button>
            </Link>
          </div>
          <div>
            <Link href="/introgame">
              <button className="buttonback">BACK</button>
            </Link>
          </div>
        </div>
      </div>
    </header>
  );
}
export default playergame;
