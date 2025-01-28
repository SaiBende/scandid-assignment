import  { useEffect, useState } from "react";
import axios from "axios";

const ProductTable = () => {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        axios
            .get("http://localhost:8080/api/products")
            .then((response) => {
                setProducts(response.data);
                setLoading(false);
            })
            .catch((error) => {
                setError(error.message);
                setLoading(false);
            });
    }, []);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error}</p>;

    return (
        <>

        {products.length > 0 && ( 
           <div className="relative overflow-x-auto">
           <table className="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
               <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                   <tr>
                       <th scope="col" className="px-6 py-3">Product ID</th>
                       <th scope="col" className="px-6 py-3">Title</th>
                       <th scope="col" className="px-6 py-3">Category</th>
                       <th scope="col" className="px-6 py-3">Price</th>
                   </tr>
               </thead>
               <tbody>
                   {products.map((item) => (
                       <tr
                           key={item.productId}
                           className="bg-white border-b dark:bg-gray-800 dark:border-gray-700 border-gray-200"
                       >
                           <td className="px-6 py-4">{item.productId}</td>
                           <td className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                               {item.title}
                           </td>
                           <td className="px-6 py-4">{item.category.categoryName}</td>
                           <td className="px-6 py-4">${item.price.toFixed(2)}</td>
                       </tr>
                   ))}
               </tbody>
           </table>
       </div>)
        }
        {products.length === 0 && <p>No products found.</p>}

       
        </>
    );

};

export default ProductTable;
