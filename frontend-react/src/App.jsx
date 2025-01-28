import { useState } from "react";
import "./App.css";
import CategoryTable from "./Components/CategoryTable";
import ProductTable from "./Components/ProductTable";
import RefreshButton from "./Components/RefreshButton";
import TransactionTable from "./Components/TransactionTable";
import Upload from "./Components/Upload";

function App() {
  const [currentComponent, setCurrentComponent] = useState(0);

  const components = [
    
    { name: "Product Table", component: <ProductTable /> },
    { name: "Category Table", component: <CategoryTable /> },
    { name: "Transaction Table", component: <TransactionTable /> },
  ];

  const handleNext = () => {
    setCurrentComponent((prev) => (prev + 1) % components.length);
  };

  const handlePrev = () => {
    setCurrentComponent((prev) =>
      prev === 0 ? components.length - 1 : prev - 1
    );
  };

  return (
    <div className="flex flex-col items-center gap-4 p-4">
      <h1 className="text-4xl text-center font-bold mb-6">
        Normalize Your Data Using Java and SQL
      </h1>
      <Upload/>
      <div className="w-full p-4 bg-gray-100 rounded-lg shadow-md">
        <RefreshButton />
      </div>

      <div className="flex gap-4 mt-6">{components.map((component, index) => (
          <button key={index} onClick={() => setCurrentComponent(index)} className="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600">
            {component.name}
          </button>))}
      </div>
      <p className="mt-2 text-gray-500">
        Current View:{"  "}
        <span className="font-semibold text-gray-800">
          {components[currentComponent].name}
        </span>
      </p>
      
     
    
      <div className="w-full p-4 bg-white rounded-lg shadow-lg">
        {components[currentComponent].component}
      </div>
      <div className="flex gap-4 mt-6">
        <button
          onClick={handlePrev}
          className="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600"
        >
          Previous
        </button>
        <button
          onClick={handleNext}
          className="px-4 py-2 bg-green-500 text-white rounded-lg hover:bg-green-600"
        >
          Next
        </button>
      </div>
      <p className="mt-2 text-gray-500">
        Current View:{"  "}
        <span className="font-semibold text-gray-800">
          {components[currentComponent].name}
        </span>
      </p>
    </div>
  );
}

export default App;
