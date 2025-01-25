
import './App.css'
import CategoryTable from './Components/CategoryTable'
import ProductTable from './Components/ProductTable'
import RefreshButton from './Components/RefreshButton'
import TransactionTable from './Components/TransactionTable'
import Upload from './Components/Upload'

function App() {
  

  return (
    <>
    <h1 className='text-4xl text-center '>Normalize Your Data Using Java and SQL </h1>
    <Upload/>

    <RefreshButton/>
    <ProductTable/>
    <CategoryTable/>
    <TransactionTable/>
    </>
  )
}

export default App
