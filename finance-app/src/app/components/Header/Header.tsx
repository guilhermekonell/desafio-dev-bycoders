import Link from "next/link";
import styles from "./Header.module.css";

import { Saira_Stencil_One } from "next/font/google";

const sairaStencilOne = Saira_Stencil_One({
  weight: ["400"],
  subsets: ["latin"],
});

export default function Header() {
  return (
    <header className={styles.header}>
      <Link href="/" className={`${sairaStencilOne.className} ${styles.logo}`}>
        FinanceApp
      </Link>
      <div className={styles.menuContainer}>
        <Link href="/cnab" className={styles.menuItem}>
          CNAB
        </Link>
        <Link href="/stores" className={styles.menuItem}>
          Lojas
        </Link>
      </div>
    </header>
  );
}
