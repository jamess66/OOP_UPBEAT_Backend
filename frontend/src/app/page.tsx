import Image from "next/image";
import Link from "next/link";
import Button from "./component/Button";

export default function Home() {
  return (
    <main className="background justify-center items-center flex flex-col ">
      <Image src="/UPBEATfont.png" alt="Cover" width={800} height={600} />
      <div className=" mt-16">
        <Link href="/introgame">
          <Button text="play" />
        </Link>
      </div>
    </main>
  );
}
