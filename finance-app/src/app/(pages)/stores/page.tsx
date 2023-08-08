"use client";

import moment from "moment";
import styles from "./page.module.css";

import React, { useEffect, useState } from "react";

type Store = {
  storeName: string;
  transactions: Transaction[];
  totalAmount: number;
};

type Transaction = {
  id: number;
  type: String;
  date: Date;
  amount: number;
  cpf: String;
  card: String;
  time: String;
  storeOwner: String;
};

export default function StoresPage() {
  const [storeTransactions, setStoreTransactions] = useState<Store[]>([]);

  useEffect(() => {
    fetch("http://localhost:8080/v1/store/transactions")
      .then((res) => res.json())
      .then((data) => setStoreTransactions(data));
  }, []);

  return (
    <div className={styles.container}>
      {storeTransactions?.map((store, index) => {
        return (
          <div key={index}>
            <h2>
              {store.storeName} - (Valor Total: R${" "}
              {store.totalAmount?.toFixed(2)})
            </h2>
            <table className={styles.table}>
              <thead>
                <tr>
                  <th>Tipo</th>
                  <th>Data</th>
                  <th>Valor</th>
                  <th>CPF</th>
                  <th>Cartão</th>
                  <th>Hora</th>
                  <th>Dono da loja</th>
                </tr>
              </thead>
              <tbody>
                {store.transactions.map((value) => {
                  return (
                    <tr key={value.id}>
                      <td>{value.type}</td>
                      <td>
                        {moment(new Date(value.date)).format("DD/MM/YYYY")}
                      </td>
                      <td>R$ {value.amount?.toFixed(2)}</td>
                      <td>
                        {value.cpf?.replace(
                          /(\d{3})(\d{3})(\d{3})(\d{2})/,
                          "$1.$2.$3-$4"
                        )}
                      </td>
                      <td>{value.card}</td>
                      <td>{value.time}</td>
                      <td>{value.storeOwner}</td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
          </div>
        );
      })}
    </div>
  );
}
