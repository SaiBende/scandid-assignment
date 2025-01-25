import { useEffect, useState } from "react";
import axios from "axios";

const CategoryTable = () => {
    const [categories, setCategories] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        axios
            .get("http://localhost:8080/api/category/allcategories")
            .then((response) => {
                setCategories(response.data);
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
        {categories.length > 0 && ( <div className="space-y-6">
            <h2>Category Table by Each Category</h2>
            {categories.map((category) => (
                <div key={category.category_id} className="border rounded-lg p-6 bg-gray-50 dark:bg-gray-800">
                    <h2 className="text-lg font-semibold text-gray-800 dark:text-white">
                        {category.categoryName}
                    </h2>
                    <p className="text-sm text-gray-600 dark:text-gray-400 mb-4">
                        {category.category_description}
                    </p>
                    <div className="overflow-x-auto">
                        <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                            <thead className="text-xs text-gray-700 uppercase bg-gray-200 dark:bg-gray-700 dark:text-gray-400">
                                <tr>
                                    <th scope="col" className="px-6 py-3">Product Name</th>
                                    <th scope="col" className="px-6 py-3">Description</th>
                                    <th scope="col" className="px-6 py-3">Brand</th>
                                    <th scope="col" className="px-6 py-3">Price</th>
                                    <th scope="col" className="px-6 py-3">Quantity</th>
                                </tr>
                            </thead>
                            <tbody>
                                {category.products.map((product) => (
                                    <tr
                                        key={product.product_id}
                                        className="bg-white border-b dark:bg-gray-900 dark:border-gray-700"
                                    >
                                        <th
                                            scope="row"
                                            className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
                                        >
                                            {product.product_name}
                                        </th>
                                        <td className="px-6 py-4">{product.product_description}</td>
                                        <td className="px-6 py-4">{product.brand}</td>
                                        <td className="px-6 py-4">${product.product_price}</td>
                                        <td className="px-6 py-4">{product.quantity}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            ))}
        </div>)}

        {categories.length === 0 && <p>No categories found.</p>}
        
        </>
       
    );
};

export default CategoryTable;