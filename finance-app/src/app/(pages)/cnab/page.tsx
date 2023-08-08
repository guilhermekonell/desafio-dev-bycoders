"use client";

import styles from "./page.module.css";

import { ChangeEvent, FormEvent, useState } from "react";
import FileInput from "../../components/FileInput/FileInput";
import { toast } from "react-toastify";

export default function CnabPage() {
  const [loading, setLoading] = useState(false);
  const [file, setFile] = useState<File | null>(null);

  const handleFileChange = (e: ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setFile(e.target.files[0]);
    }
  };

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();

    if (!file) {
      toast.warn("Selecione um arquivo");
      return;
    }

    setLoading(true);
    const formData = new FormData();
    formData.append("file", file as File);

    const response = await fetch("http://localhost:8080/v1/cnab/upload", {
      method: "POST",
      body: formData,
    });

    if (response.status !== 200) {
      toast.error("Não foi possível processar o arquivo selecionado");
      return;
    }

    toast.success("Arquivo processado com sucesso");
    setLoading(false);
  };

  return (
    <form className={styles.form} onSubmit={handleSubmit}>
      <FileInput onFileChange={handleFileChange} />
      <span>{file?.name}</span>
      <button className={styles.button} type="submit" disabled={loading}>
        {loading ? "Processando..." : "Processar arquivo"}
      </button>
    </form>
  );
}
