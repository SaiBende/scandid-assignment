import  { useEffect, useState } from "react";
import axios from "axios";
//http://localhost:8080/api/transaction/alltransaction



const TransactionTable = () => {
  const [transactions, setTransactions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchTransactions = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/transaction/alltransaction"); // Replace with your API URL
        setTransactions(response.data);
        setLoading(false);
      } catch  {
        setError("Failed to fetch transactions. Please try again.");
        setLoading(false);
      }
    };

    fetchTransactions();
  }, []);

  if (loading) {
    return <p className="text-center text-gray-500">Loading...</p>;
  }

  if (error) {
    return <p className="text-center text-red-500">{error}</p>;
  }

  return (<>

  {transactions.length > 0 && (<div className="relative overflow-x-auto">
        <h2>Transaction Table</h2>
      <table className="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
        <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
          <tr>
            <th scope="col" className="px-6 py-3">Transaction ID</th>
            <th scope="col" className="px-6 py-3">Product Name</th>
            <th scope="col" className="px-6 py-3">Quantity</th>
            <th scope="col" className="px-6 py-3">Price</th>
            <th scope="col" className="px-6 py-3">Transaction Date</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((transaction) => (
            <tr
              key={transaction.transaction_id}
              className="bg-white border-b dark:bg-gray-800 dark:border-gray-700 border-gray-200"
            >
              <th
                scope="row"
                className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
              >
                {transaction.transaction_id}
              </th>
              <td className="px-6 py-4">{transaction.product.product_name}</td>
              <td className="px-6 py-4">{transaction.quantity}</td>
              <td className="px-6 py-4">
                ${transaction.product.product_price.toFixed(2)}
              </td>
              <td className="px-6 py-4">
                {new Date(transaction.transactionDate).toLocaleDateString()}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>)}
    {transactions.length === 0 && (<p>No transactions found.</p>)}
    
  </>
  );
};

export default TransactionTable;
