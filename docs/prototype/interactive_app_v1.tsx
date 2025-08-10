import React, { useState } from 'react';

const FinancialApp = () => {
  const [currentScreen, setCurrentScreen] = useState('dashboard');
  const [accounts, setAccounts] = useState([
    { id: 1, name: 'Main Checking', type: 'Bank Account', balance: 8247.50, icon: '🏦' },
    { id: 2, name: 'Emergency Fund', type: 'Savings', balance: 3200.00, icon: '💰' },
    { id: 3, name: 'Credit Card', type: 'Credit', balance: -450.00, icon: '💳' },
    { id: 4, name: 'Cash Wallet', type: 'Cash', balance: 1850.00, icon: '💵' }
  ]);

  const [recurringItems, setRecurringItems] = useState([
    { id: 1, title: 'Monthly Salary', amount: 2800, type: 'income', date: 'Jan 15, 2025', frequency: 'Monthly' },
    { id: 2, title: 'Freelance Project', amount: 1200, type: 'income', date: 'Jan 20, 2025', frequency: 'Monthly' },
    { id: 3, title: 'Rent Payment', amount: -1200, type: 'expense', date: 'Jan 1, 2025', frequency: 'Monthly' },
    { id: 4, title: 'Car Insurance', amount: -150, type: 'expense', date: 'Jan 5, 2025', frequency: 'Monthly' },
    { id: 5, title: 'Groceries Budget', amount: -400, type: 'expense', date: 'Monthly', frequency: 'Monthly' }
  ]);

  const [selectedAccount, setSelectedAccount] = useState(accounts[0]);
  const [newBalance, setNewBalance] = useState('');
  const [transferForm, setTransferForm] = useState({
    from: accounts[0],
    to: accounts[1],
    amount: '500.00'
  });

  const [newRecurringItem, setNewRecurringItem] = useState({
    type: 'Income',
    title: '',
    amount: '',
    frequency: 'Monthly',
    date: '2025-01-15',
    account: 'Main Checking',
    category: '',
    notes: ''
  });

  const totalBalance = accounts.reduce((sum, account) => sum + account.balance, 0);

  const StatusBar = () => (
    <div className="bg-gray-800 h-8 flex justify-between items-center px-4 text-xs font-semibold text-gray-400 border-b border-gray-700">
      <span>9:41</span>
      <span>100% 🔋</span>
    </div>
  );

  const ScreenHeader = ({ title, showBack = false }) => (
    <div className="bg-gray-900 text-white p-4 flex items-center border-b border-gray-700">
      {showBack && (
        <button 
          className="text-lg text-indigo-400 mr-4 cursor-pointer"
          onClick={() => {
            if (currentScreen === 'add-recurring') {
              setCurrentScreen('projections');
            } else {
              setCurrentScreen('dashboard');
            }
          }}
        >
          ‹
        </button>
      )}
      <div className="text-base font-semibold flex-1">{title}</div>
    </div>
  );

  const BottomNav = ({ activeTab }) => (
    <div className="absolute bottom-0 left-0 right-0 bg-gray-800 border-t border-gray-700 flex justify-around py-2">
      {[
        { id: 'dashboard', label: 'Home' },
        { id: 'accounts', label: 'Accounts' },
        { id: 'history', label: 'History' },
        { id: 'projections', label: 'Projects' }
      ].map(tab => (
        <button
          key={tab.id}
          className={`text-center text-xs flex-1 ${
            activeTab === tab.id ? 'text-indigo-400 font-semibold' : 'text-gray-400'
          }`}
          onClick={() => setCurrentScreen(tab.id)}
        >
          <div className={`w-5 h-5 bg-current rounded mx-auto mb-1 ${
            activeTab === tab.id ? 'opacity-100' : 'opacity-70'
          }`}></div>
          {tab.label}
        </button>
      ))}
    </div>
  );

  const DashboardScreen = () => (
    <div className="h-full">
      <StatusBar />
      <ScreenHeader title="Financial Overview" />
      <div className="p-5 pb-24 h-full overflow-y-auto bg-gray-900">
        <div className="bg-gradient-to-br from-indigo-600 to-purple-600 text-white p-5 rounded-xl text-center mb-5 shadow-lg">
          <div className="text-3xl font-bold mb-1">${totalBalance.toLocaleString()}</div>
          <div className="text-xs opacity-90">Total Balance</div>
        </div>

        <div className="flex gap-3 mb-5">
          <div className="flex-1 bg-gray-800 p-4 rounded-lg text-center border border-gray-700">
            <div className="text-lg font-semibold text-indigo-400 mb-1">$13,200</div>
            <div className="text-xs text-gray-400">This Month</div>
          </div>
          <div className="flex-1 bg-gray-800 p-4 rounded-lg text-center border border-gray-700">
            <div className="text-lg font-semibold text-indigo-400 mb-1">$14,100</div>
            <div className="text-xs text-gray-400">Next Month</div>
          </div>
        </div>

        <div className="grid grid-cols-2 gap-3 mb-5">
          <button 
            className="bg-gray-800 border-2 border-indigo-400 text-indigo-400 p-4 rounded-lg text-center text-xs font-semibold cursor-pointer transition-all hover:bg-indigo-400 hover:text-white"
            onClick={() => setCurrentScreen('update-balance')}
          >
            💰 Update Balance
          </button>
          <button 
            className="bg-gray-800 border-2 border-indigo-400 text-indigo-400 p-4 rounded-lg text-center text-xs font-semibold cursor-pointer transition-all hover:bg-indigo-400 hover:text-white"
            onClick={() => setCurrentScreen('transfer')}
          >
            ↔️ Transfer Money
          </button>
        </div>

        <div className="mt-5">
          <div className="text-sm font-semibold mb-3 text-gray-200">Recent Balance Updates</div>
          {[
            { account: 'Main Checking', time: '2 hours ago', amount: '+$2,400', positive: true },
            { account: 'Cash Wallet', time: 'Yesterday', amount: '-$85', positive: false },
            { account: 'Savings', time: '3 days ago', amount: '+$500', positive: true }
          ].map((item, index) => (
            <div key={index} className="flex justify-between items-center py-3 border-b border-gray-700 last:border-b-0">
              <div className="flex-1">
                <div className="text-xs font-semibold text-gray-200">{item.account}</div>
                <div className="text-xs text-gray-400">{item.time}</div>
              </div>
              <div className={`text-xs font-semibold ${item.positive ? 'text-green-400' : 'text-red-400'}`}>
                {item.amount}
              </div>
            </div>
          ))}
        </div>
      </div>
      <BottomNav activeTab="dashboard" />
    </div>
  );

  const AccountsScreen = () => (
    <div className="h-full">
      <StatusBar />
      <ScreenHeader title="My Accounts" />
      <div className="p-5 pb-24 h-full overflow-y-auto bg-gray-900">
        {accounts.map(account => (
          <div key={account.id} className="flex items-center bg-gray-800 border border-gray-700 rounded-lg p-4 mb-3 cursor-pointer transition-all hover:border-indigo-400 hover:shadow-lg">
            <div className="w-10 h-10 bg-gradient-to-br from-indigo-600 to-purple-600 rounded-full flex items-center justify-center text-white text-base mr-4">
              {account.icon}
            </div>
            <div className="flex-1">
              <div className="text-sm font-semibold text-gray-200 mb-1">{account.name}</div>
              <div className="text-xs text-gray-400">{account.type}</div>
            </div>
            <div className={`text-base font-bold ${account.balance < 0 ? 'text-red-400' : 'text-gray-200'}`}>
              ${Math.abs(account.balance).toLocaleString()}
            </div>
          </div>
        ))}
      </div>
      <button 
        className="absolute bottom-20 right-5 w-12 h-12 bg-gradient-to-br from-indigo-600 to-purple-600 text-white border-none rounded-full text-2xl cursor-pointer shadow-lg"
        onClick={() => alert('Add Account feature coming soon!')}
      >
        +
      </button>
      <BottomNav activeTab="accounts" />
    </div>
  );

  const HistoryScreen = () => (
    <div className="h-full">
      <StatusBar />
      <ScreenHeader title="Balance History" />
      <div className="p-5 pb-24 h-full overflow-y-auto bg-gray-900">
        {[
          { day: '15', month: 'DEC', account: 'Main Checking', note: 'Salary deposit', change: '+$2,400', balance: '$8,247.50', positive: true },
          { day: '14', month: 'DEC', account: 'Cash Wallet', note: 'ATM withdrawal', change: '-$200', balance: '$1,850.00', positive: false },
          { day: '12', month: 'DEC', account: 'Emergency Fund', note: 'Monthly savings', change: '+$500', balance: '$3,200.00', positive: true },
          { day: '10', month: 'DEC', account: 'Credit Card', note: 'Online purchase', change: '-$85', balance: '-$450.00', positive: false },
          { day: '08', month: 'DEC', account: 'Main Checking', note: 'Rent payment', change: '-$1,200', balance: '$5,847.50', positive: false }
        ].map((item, index) => (
          <div key={index} className="flex items-center py-3 border-b border-gray-700">
            <div className="w-15 text-center mr-4">
              <div className="text-base font-bold text-gray-200">{item.day}</div>
              <div className="text-xs text-gray-400">{item.month}</div>
            </div>
            <div className="flex-1">
              <div className="text-xs font-semibold text-gray-200 mb-1">{item.account}</div>
              <div className="text-xs text-gray-400">{item.note}</div>
            </div>
            <div className="text-right">
              <div className={`text-xs font-semibold mb-1 ${item.positive ? 'text-green-400' : 'text-red-400'}`}>
                {item.change}
              </div>
              <div className={`text-xs ${item.balance.includes('-') ? 'text-red-400' : 'text-gray-400'}`}>
                {item.balance}
              </div>
            </div>
          </div>
        ))}
      </div>
      <BottomNav activeTab="history" />
    </div>
  );

  const ProjectionsScreen = () => (
    <div className="h-full">
      <StatusBar />
      <ScreenHeader title="Balance Projections" />
      <div className="p-5 pb-24 h-full overflow-y-auto bg-gray-900">
        <div className="flex items-center justify-center gap-4 mb-5 p-3 bg-gray-800 rounded-lg border border-gray-700">
          <div className="text-lg text-indigo-400 cursor-pointer p-1">‹</div>
          <div className="text-sm font-semibold text-gray-200 min-w-30 text-center">January 2025</div>
          <div className="text-lg text-indigo-400 cursor-pointer p-1">›</div>
        </div>

        <div className="bg-gradient-to-br from-green-700 to-green-500 text-white p-5 rounded-xl text-center mb-5">
          <div className="text-sm opacity-90 mb-1">January 2025</div>
          <div className="text-2xl font-bold">$14,100.00</div>
        </div>

        <div className="mb-5">
          <div className="text-sm font-semibold mb-3 text-gray-200">Expected Income</div>
          {recurringItems.filter(item => item.type === 'income').map(item => (
            <div key={item.id} className="flex justify-between items-center p-3 bg-gray-800 rounded-md mb-2 border border-gray-700">
              <div className="flex-1">
                <div className="text-xs font-semibold text-gray-200">{item.title}</div>
                <div className="text-xs text-gray-400">{item.date}</div>
              </div>
              <div className="text-xs font-semibold text-green-400">+${item.amount}</div>
            </div>
          ))}
        </div>

        <div className="mb-5">
          <div className="text-sm font-semibold mb-3 text-gray-200">Expected Expenses</div>
          {recurringItems.filter(item => item.type === 'expense').map(item => (
            <div key={item.id} className="flex justify-between items-center p-3 bg-gray-800 rounded-md mb-2 border border-gray-700">
              <div className="flex-1">
                <div className="text-xs font-semibold text-gray-200">{item.title}</div>
                <div className="text-xs text-gray-400">{item.date}</div>
              </div>
              <div className="text-xs font-semibold text-red-400">${item.amount}</div>
            </div>
          ))}
        </div>
      </div>
      <button 
        className="absolute bottom-20 right-5 w-12 h-12 bg-gradient-to-br from-indigo-600 to-purple-600 text-white border-none rounded-full text-2xl cursor-pointer shadow-lg"
        onClick={() => setCurrentScreen('add-recurring')}
      >
        +
      </button>
      <BottomNav activeTab="projections" />
    </div>
  );

  const UpdateBalanceScreen = () => (
    <div className="h-full">
      <StatusBar />
      <ScreenHeader title="Update Balance" showBack={true} />
      <div className="p-5 h-full overflow-y-auto bg-gray-900">
        <div className="mb-5">
          <div className="text-xs font-semibold text-gray-200 mb-2">Account</div>
          <select 
            className="w-full p-3 border border-gray-700 rounded-lg text-sm bg-gray-800 text-gray-200"
            value={selectedAccount.name}
            onChange={(e) => setSelectedAccount(accounts.find(acc => acc.name === e.target.value))}
          >
            {accounts.map(account => (
              <option key={account.id} value={account.name}>{account.name}</option>
            ))}
          </select>
        </div>

        <div className="bg-gray-800 border border-gray-700 rounded-lg p-4 text-center mb-5">
          <div className="text-xs text-gray-400 mb-1">Current Balance</div>
          <div className="text-xl font-bold text-gray-200">${selectedAccount.balance.toLocaleString()}</div>
        </div>

        <div className="mb-5">
          <div className="text-xs font-semibold text-gray-200 mb-2">New Balance</div>
          <input 
            type="text" 
            className="w-full p-3 border border-gray-700 rounded-lg text-2xl font-bold text-center text-indigo-400 bg-gray-800"
            placeholder="$0.00"
            value={newBalance}
            onChange={(e) => setNewBalance(e.target.value)}
          />
        </div>

        {newBalance && (
          <div className="bg-green-900 border border-green-700 rounded-lg p-3 text-center mb-5">
            <div className="text-xs text-gray-400 mb-1">Change</div>
            <div className="text-base font-bold text-green-400">+$400.00</div>
          </div>
        )}

        <div className="mb-5">
          <div className="text-xs font-semibold text-gray-200 mb-2">Notes (Optional)</div>
          <textarea 
            className="w-full p-3 border border-gray-700 rounded-lg text-sm bg-gray-800 text-gray-200 h-15 resize-none"
            placeholder="What caused this change?"
            defaultValue="Bonus payment received"
          />
        </div>

        <div className="mb-5">
          <div className="text-xs font-semibold text-gray-200 mb-2">Date</div>
          <input 
            type="date" 
            className="w-full p-3 border border-gray-700 rounded-lg text-sm bg-gray-800 text-gray-200"
            defaultValue="2024-12-15"
          />
        </div>

        <div className="flex gap-3 mt-8">
          <button 
            className="flex-1 p-4 bg-gray-800 text-indigo-400 border-2 border-indigo-400 rounded-lg text-sm font-semibold cursor-pointer transition-all"
            onClick={() => setCurrentScreen('dashboard')}
          >
            Cancel
          </button>
          <button 
            className="flex-1 p-4 bg-gradient-to-br from-indigo-600 to-purple-600 text-white border-none rounded-lg text-sm font-semibold cursor-pointer transition-all"
            onClick={() => setCurrentScreen('dashboard')}
          >
            Update Balance
          </button>
        </div>
      </div>
    </div>
  );

  const TransferScreen = () => (
    <div className="h-full">
      <StatusBar />
      <ScreenHeader title="Transfer Money" showBack={true} />
      <div className="p-5 h-full overflow-y-auto bg-gray-900">
        <div className="bg-gray-800 rounded-xl p-5 mb-6 text-center border border-gray-700">
          <div className="bg-gray-900 border border-gray-700 rounded-lg p-3 flex items-center gap-3 mb-3">
            <div className="w-8 h-8 bg-gradient-to-br from-indigo-600 to-purple-600 rounded-full flex items-center justify-center text-xs">
              🏦
            </div>
            <div className="flex-1 text-left">
              <div className="text-xs font-semibold text-gray-200">Main Checking</div>
              <div className="text-xs text-gray-400">$8,247.50</div>
            </div>
          </div>
          
          <div className="text-2xl text-indigo-400 my-3">↓</div>
          
          <div className="bg-gray-900 border border-gray-700 rounded-lg p-3 flex items-center gap-3">
            <div className="w-8 h-8 bg-gradient-to-br from-indigo-600 to-purple-600 rounded-full flex items-center justify-center text-xs">
              💰
            </div>
            <div className="flex-1 text-left">
              <div className="text-xs font-semibold text-gray-200">Emergency Fund</div>
              <div className="text-xs text-gray-400">$3,200.00</div>
            </div>
          </div>
        </div>

        <div className="mb-5">
          <div className="text-xs font-semibold text-gray-200 mb-2">From Account</div>
          <select className="w-full p-3 border border-gray-700 rounded-lg text-sm bg-gray-800 text-gray-200">
            <option>Main Checking</option>
            <option>Cash Wallet</option>
            <option>Emergency Fund</option>
          </select>
        </div>

        <div className="mb-5">
          <div className="text-xs font-semibold text-gray-200 mb-2">To Account</div>
          <select className="w-full p-3 border border-gray-700 rounded-lg text-sm bg-gray-800 text-gray-200">
            <option>Emergency Fund</option>
            <option>Cash Wallet</option>
            <option>Main Checking</option>
          </select>
        </div>

        <div className="mb-5">
          <div className="text-xs font-semibold text-gray-200 mb-2">Amount</div>
          <input 
            type="text" 
            className="w-full p-3 border border-gray-700 rounded-lg text-2xl font-bold text-center text-indigo-400 bg-gray-800"
            placeholder="$0.00"
            value={`$${transferForm.amount}`}
            onChange={(e) => setTransferForm({...transferForm, amount: e.target.value.replace('$', '')})}
          />
        </div>

        <div className="mb-5">
          <div className="text-xs font-semibold text-gray-200 mb-2">Description (Optional)</div>
          <input 
            type="text" 
            className="w-full p-3 border border-gray-700 rounded-lg text-sm bg-gray-800 text-gray-200"
            placeholder="Monthly savings transfer"
            defaultValue="Monthly savings"
          />
        </div>

        <div className="flex gap-3 mt-8">
          <button 
            className="flex-1 p-4 bg-gray-800 text-indigo-400 border-2 border-indigo-400 rounded-lg text-sm font-semibold cursor-pointer"
            onClick={() => setCurrentScreen('dashboard')}
          >
            Cancel
          </button>
          <button 
            className="flex-1 p-4 bg-gradient-to-br from-indigo-600 to-purple-600 text-white border-none rounded-lg text-sm font-semibold cursor-pointer"
            onClick={() => setCurrentScreen('dashboard')}
          >
            Transfer
          </button>
        </div>
      </div>
    </div>
  );

  const AddRecurringScreen = () => (
    <div className="h-full">
      <StatusBar />
      <ScreenHeader title="Add Recurring Item" showBack={true} />
      <div className="p-5 h-full overflow-y-auto bg-gray-900">
        <div className="mb-5">
          <div className="text-xs font-semibold text-gray-200 mb-2">Type</div>
          <select 
            className="w-full p-3 border border-gray-700 rounded-lg text-sm bg-gray-800 text-gray-200"
            value={newRecurringItem.type}
            onChange={(e) => setNewRecurringItem({...newRecurringItem, type: e.target.value})}
          >
            <option>Income</option>
            <option>Expense</option>
          </select>
        </div>

        <div className="mb-5">
          <div className="text-xs font-semibold text-gray-200 mb-2">Title</div>
          <input 
            type="text" 
            className="w-full p-3 border border-gray-700 rounded-lg text-sm bg-gray-800 text-gray-200"
            placeholder="e.g., Monthly Salary, Rent Payment"
            value={newRecurringItem.title}
            onChange={(e) => setNewRecurringItem({...newRecurringItem, title: e.target.value})}
          />
        </div>

        <div className="mb-5">
          <div className="text-xs font-semibold text-gray-200 mb-2">Amount</div>
          <input 
            type="text" 
            className="w-full p-3 border border-gray-700 rounded-lg text-2xl font-bold text-center text-indigo-400 bg-gray-800"
            placeholder="$0.00"
            value={newRecurringItem.amount}
            onChange={(e) => setNewRecurringItem({...newRecurringItem, amount: e.target.value})}
          />
        </div>

        <div className="mb-5">
          <div className="text-xs font-semibold text-gray-200 mb-2">Frequency</div>
          <select 
            className="w-full p-3 border border-gray-700 rounded-lg text-sm bg-gray-800 text-gray-200"
            value={newRecurringItem.frequency}
            onChange={(e) => setNewRecurringItem({...newRecurringItem, frequency: e.target.value})}
          >
            <option>Monthly</option>
            <option>Weekly</option>
            <option>Bi-weekly</option>
            <option>Quarterly</option>
            <option>Yearly</option>
          </select>
        </div>

        <div className="mb-5">
          <div className="text-xs font-semibold text-gray-200 mb-2">Next Date</div>
          <input 
            type="date" 
            className="w-full p-3 border border-gray-700 rounded-lg text-sm bg-gray-800 text-gray-200"
            value={newRecurringItem.date}
            onChange={(e) => setNewRecurringItem({...newRecurringItem, date: e.target.value})}
          />
        </div>

        <div className="mb-5">
          <div className="text-xs font-semibold text-gray-200 mb-2">Account</div>
          <select 
            className="w-full p-3 border border-gray-700 rounded-lg text-sm bg-gray-800 text-gray-200"
            value={newRecurringItem.account}
            onChange={(e) => setNewRecurringItem({...newRecurringItem, account: e.target.value})}
          >
            {accounts.map(account => (
              <option key={account.id} value={account.name}>{account.name}</option>
            ))}
          </select>
        </div>

        <div className="mb-5">
          <div className="text-xs font-semibold text-gray-200 mb-2">Category (Optional)</div>
          <select 
            className="w-full p-3 border border-gray-700 rounded-lg text-sm bg-gray-800 text-gray-200"
            value={newRecurringItem.category}
            onChange={(e) => setNewRecurringItem({...newRecurringItem, category: e.target.value})}
          >
            <option value="">Select Category</option>
            <option>Salary</option>
            <option>Freelance</option>
            <option>Rent/Mortgage</option>
            <option>Insurance</option>
            <option>Utilities</option>
            <option>Groceries</option>
            <option>Transportation</option>
            <option>Entertainment</option>
            <option>Other</option>
          </select>
        </div>

        <div className="mb-5">
          <div className="text-xs font-semibold text-gray-200 mb-2">Notes (Optional)</div>
          <textarea 
            className="w-full p-3 border border-gray-700 rounded-lg text-sm bg-gray-800 text-gray-200 h-15 resize-none"
            placeholder="Any additional details about this recurring item"
            value={newRecurringItem.notes}
            onChange={(e) => setNewRecurringItem({...newRecurringItem, notes: e.target.value})}
          />
        </div>

        <div className="flex gap-3 mt-8">
          <button 
            className="flex-1 p-4 bg-gray-800 text-indigo-400 border-2 border-indigo-400 rounded-lg text-sm font-semibold cursor-pointer"
            onClick={() => setCurrentScreen('projections')}
          >
            Cancel
          </button>
          <button 
            className="flex-1 p-4 bg-gradient-to-br from-indigo-600 to-purple-600 text-white border-none rounded-lg text-sm font-semibold cursor-pointer"
            onClick={() => {
              if (newRecurringItem.title && newRecurringItem.amount) {
                const newItem = {
                  id: recurringItems.length + 1,
                  title: newRecurringItem.title,
                  amount: newRecurringItem.type === 'Income' ? 
                    parseFloat(newRecurringItem.amount.replace('$', '') || '0') : 
                    -parseFloat(newRecurringItem.amount.replace('$', '') || '0'),
                  type: newRecurringItem.type.toLowerCase(),
                  date: new Date(newRecurringItem.date).toLocaleDateString('en-US', { 
                    month: 'short', 
                    day: 'numeric', 
                    year: 'numeric' 
                  }),
                  frequency: newRecurringItem.frequency
                };
                setRecurringItems([...recurringItems, newItem]);
                setNewRecurringItem({
                  type: 'Income',
                  title: '',
                  amount: '',
                  frequency: 'Monthly',
                  date: '2025-01-15',
                  account: 'Main Checking',
                  category: '',
                  notes: ''
                });
                setCurrentScreen('projections');
              }
            }}
          >
            Add Recurring Item
          </button>
        </div>
      </div>
    </div>
  );

  // Phone container with proper dimensions
  return (
    <div className="min-h-screen bg-gradient-to-br from-gray-900 via-blue-900 to-indigo-900 flex items-center justify-center p-4">
      <div className="bg-gray-900 rounded-3xl p-4 shadow-2xl border border-gray-700" style={{width: '375px', height: '812px'}}>
        <div className="bg-gray-800 rounded-2xl h-full overflow-hidden relative border border-gray-700">
          {currentScreen === 'dashboard' && <DashboardScreen />}
          {currentScreen === 'accounts' && <AccountsScreen />}
          {currentScreen === 'history' && <HistoryScreen />}
          {currentScreen === 'projections' && <ProjectionsScreen />}
          {currentScreen === 'update-balance' && <UpdateBalanceScreen />}
          {currentScreen === 'transfer' && <TransferScreen />}
          {currentScreen === 'add-recurring' && <AddRecurringScreen />}
        </div>
      </div>
    </div>
  );
};

export default FinancialApp;
                