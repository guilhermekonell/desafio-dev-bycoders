"use client";

import styles from "./FileInput.module.css";

import { ChangeEvent } from "react";

type FileInputProps = {
  onFileChange: (e: ChangeEvent<HTMLInputElement>) => void;
};

export default function FileInput({ onFileChange }: FileInputProps) {
  return (
    <div className={styles.container}>
      <label htmlFor="file_input">
        <input
          className={styles.input}
          id="file_input"
          type="file"
          accept=".txt"
          onChange={onFileChange}
        />
        <span className={styles.span}>Selecione um arquivo CNAB</span>
      </label>
    </div>
  );
}
