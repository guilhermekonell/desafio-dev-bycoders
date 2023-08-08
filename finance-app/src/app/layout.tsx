import "./globals.css";
import "react-toastify/dist/ReactToastify.css";

import type { Metadata } from "next";
import { Saira } from "next/font/google";
import Header from "./components/Header/Header";
import { ToastContainer } from "react-toastify";

const saira = Saira({
  weight: ["300", "400", "500", "600"],
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "Finance App",
  description: "Finance App to control operations of stores",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body className={saira.className}>
        <Header />
        {children}
        <ToastContainer />
      </body>
    </html>
  );
}
