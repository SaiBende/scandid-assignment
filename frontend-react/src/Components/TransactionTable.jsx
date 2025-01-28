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
        const response = await axios.get("http://localhost:8080/api/transaction"); 
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

  {transactions.length > 0 && (
    <div className="relative max-w-screen overflow-x-auto">
    <table className="min-w-full table-auto w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
        <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
                <th scope="col" className="px-6 py-3">Transaction ID</th>
                <th scope="col" className="px-6 py-3">Product Name</th>
                <th scope="col" className="px-6 py-3">Category</th>
                <th scope="col" className="px-6 py-3">Store</th>
                <th scope="col" className="px-6 py-3">Price</th>
                <th scope="col" className="px-6 py-3">Sales</th>
                <th scope="col" className="px-6 py-3">Commission</th>
                <th scope="col" className="px-6 py-3">Order Date</th>
                <th scope="col" className="px-6 py-3">Status</th>
            </tr>
        </thead>
        <tbody>
            {transactions.map((item) => (
                <tr
                    key={item.txid}
                    className="bg-white border-b dark:bg-gray-800 dark:border-gray-700 border-gray-200"
                >
                    <td className="px-6 py-4">{item.txid}</td>
                    <td className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                        {item.product.title}
                    </td>
                    <td className="px-6 py-4">{item.product.category.categoryName}</td>
                    <td className="px-6 py-4">{item.store}</td>
                    <td className="px-6 py-4">${item.product.price.toFixed(2)}</td>
                    <td className="px-6 py-4">${item.sales.toFixed(2)}</td>
                    <td className="px-6 py-4">${item.commission.toFixed(2)}</td>
                    <td className="px-6 py-4">{new Date(item.orderDate).toLocaleDateString()}</td>
                    <td className="px-6 py-4">{item.status}</td>
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
