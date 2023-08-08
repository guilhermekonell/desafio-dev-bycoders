import styles from "./page.module.css";

export default function Home() {
  return (
    <main className={styles.main}>
      <h1>Bem vindo ao Finance App</h1>
      <strong>Acesse a página CNAB para importar um arquivo CNAB</strong>
      <strong>Acesse a página Lojas para listar as transações por loja</strong>
    </main>
  );
}
