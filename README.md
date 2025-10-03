# Mr. Dinner Service 🍽️

A comprehensive Java-based dinner delivery service system implementing Domain-Driven Design (DDD) principles.

## 📋 Overview

Mr. Dinner Service is a complete restaurant management system that handles:
- Customer management
- Menu and dinner package management
- Order processing and payment
- Inventory management
- Staff management (cooks and couriers)
- Delivery coordination

## 🏗️ Architecture

### Domain Layer
- **Common**: Value objects (Money, Address)
- **Customer**: Customer entity management
- **Menu**: Menu items and dinner packages with inheritance hierarchy
- **Order**: Order processing and status management
- **Staff**: Staff hierarchy (Cook, Courier)
- **Delivery**: Delivery coordination and tracking
- **Payment**: Payment processing with multiple methods
- **Inventory**: Stock management and tracking

### Service Layer
- **PricingService**: Price calculations and business rules
- **OrderService**: Order workflow management
- **InventoryService**: Inventory operations
- **DeliveryService**: Delivery coordination

## 🚀 Features

### Menu System
- **Abstract Dinner Class**: Base class for all dinner packages
- **Concrete Dinner Types**:
  - `ValentineDinner`: Romantic dinner with special pricing
  - `FrenchDinner`: French cuisine with premium pricing
  - `EnglishDinner`: Traditional English dinner with tea service
  - `ChampagneFeastDinner`: Luxury dinner (formal style only)

### Order Management
- Complete order lifecycle management
- Status transitions with validation
- Payment processing with multiple methods
- Inventory integration

### Staff Management
- **Cook**: Kitchen staff with specialties and experience tracking
- **Courier**: Delivery staff with vehicle and area management

### Inventory System
- Stock item management
- Expiration tracking
- Transaction logging
- Low stock alerts

## 🛠️ Technology Stack

- **Java**: Core application language
- **Object-Oriented Design**: Inheritance, polymorphism, encapsulation
- **Domain-Driven Design**: Rich domain models with business logic
- **State Management**: Proper state transitions and validation

## 📁 Project Structure

```
com.mrdinner/
├── app/
│   └── Main.java                 # Application entry point
├── domain/
│   ├── common/
│   │   ├── Money.java           # Value object for monetary amounts
│   │   └── Address.java         # Value object for addresses
│   ├── customer/
│   │   └── Customer.java        # Customer entity
│   ├── menu/
│   │   ├── ServingStyle.java    # Serving style enumeration
│   │   ├── MenuItem.java        # Individual menu items
│   │   ├── ItemType.java        # Item type enumeration
│   │   ├── Dinner.java          # Abstract dinner class
│   │   ├── ValentineDinner.java # Romantic dinner package
│   │   ├── FrenchDinner.java    # French cuisine dinner
│   │   ├── EnglishDinner.java   # English cuisine dinner
│   │   └── ChampagneFeastDinner.java # Luxury dinner (formal only)
│   ├── order/
│   │   ├── Order.java           # Order entity
│   │   ├── OrderItem.java       # Order line items
│   │   └── OrderStatus.java     # Order status enumeration
│   ├── staff/
│   │   ├── Staff.java           # Abstract staff class
│   │   ├── Cook.java            # Kitchen staff
│   │   └── Courier.java         # Delivery staff
│   ├── delivery/
│   │   ├── Delivery.java        # Delivery entity
│   │   └── DeliveryStatus.java  # Delivery status enumeration
│   ├── payment/
│   │   ├── Payment.java         # Payment entity
│   │   ├── PaymentStatus.java   # Payment status enumeration
│   │   └── PaymentMethod.java   # Payment method enumeration
│   └── inventory/
│       ├── StockItem.java       # Inventory item entity
│       └── Inventory.java       # Inventory management system
└── service/
    ├── PricingService.java      # Price calculation service
    ├── OrderService.java        # Order management service
    ├── InventoryService.java    # Inventory management service
    └── DeliveryService.java     # Delivery coordination service
```

## 🚀 Getting Started

### Prerequisites
- Java 8 or higher
- Git

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/mr-dinner-service.git
cd mr-dinner-service
```

2. Compile the Java files:
```bash
# For Java 8 compatibility
javac -d . -source 8 -target 8 com\mrdinner\domain\common\*.java
javac -d . -source 8 -target 8 com\mrdinner\domain\customer\*.java
javac -d . -source 8 -target 8 com\mrdinner\domain\menu\*.java
javac -d . -source 8 -target 8 com\mrdinner\domain\order\*.java
javac -d . -source 8 -target 8 com\mrdinner\domain\staff\*.java
javac -d . -source 8 -target 8 com\mrdinner\domain\delivery\*.java
javac -d . -source 8 -target 8 com\mrdinner\domain\payment\*.java
javac -d . -source 8 -target 8 com\mrdinner\domain\inventory\*.java
javac -d . -source 8 -target 8 com\mrdinner\service\*.java
javac -d . -source 8 -target 8 com\mrdinner\app\*.java
```

3. Run the application:
```bash
java com.mrdinner.app.Main
```

## 🎯 Key Features Demonstrated

### Dinner Package Inheritance
```java
// Abstract base class
Dinner dinner = new ValentineDinner("Romantic Dinner", "Special for couples", Money.of(45.99, "USD"));

// Style constraints - ChampagneFeastDinner only allows formal style
ChampagneFeastDinner luxuryDinner = new ChampagneFeastDinner("Luxury Feast", "Premium dining", Money.of(100.00, "USD"));
// Automatically set to FORMAL style and enforces it
```

### Order Processing Workflow
1. Create order with customer and delivery address
2. Add menu items and dinner packages
3. Confirm order (validates inventory and minimum order amount)
4. Process payment
5. Prepare order (kitchen workflow)
6. Assign courier and deliver

### Staff Specialization
- **Cooks** have specialties and experience levels affecting preparation time
- **Couriers** have vehicle types and delivery area coverage
- Automatic assignment based on availability and capabilities

## 🧪 Sample Output

When you run the application, you'll see:

```
=== Mr. Dinner Service - Starting Application ===

--- Initializing Services ---
Services initialized successfully

--- Creating Sample Data ---
Creating sample staff...
Sample staff created: 1 cook, 2 couriers
Creating sample menu items...
Sample menu items created: appetizer, main course, dessert
Sample data created successfully

--- Demonstrating System Functionality ---

Creating sample customer...
Customer created: Alice Johnson

Creating sample order...
Order created: [UUID]

Adding items to order...
Items added to order:
- 1x Valentine Dinner
- 1x French Dinner  
- 2x House Wine
- 1x Artisan Bread
Order total: $XXX.XX

Processing order...
Order confirmed: CONFIRMED
Payment processed: COMPLETED - $XXX.XX
Order ready for delivery: READY

Demonstrating delivery process...
Delivery completed successfully!
Courier: Mike Driver
Tip received: $8.00

--- Inventory Status ---
Inventory Statistics: [stats]

--- Delivery Statistics ---
Delivery Statistics: [stats]

=== Mr. Dinner Service - Application Completed ===
```

## 🎨 Design Patterns Used

- **Domain-Driven Design**: Rich domain models with business logic
- **Strategy Pattern**: Different dinner types with specialized pricing
- **State Pattern**: Order and delivery status management
- **Factory Pattern**: Service creation and dependency injection
- **Template Method**: Staff workflow and order processing

## 📝 Business Rules

- Minimum order amount: $15.00
- Free delivery threshold: $50.00
- Standard delivery fee: $4.99
- Tax rate: 8%
- Volume discounts for multiple items
- ChampagneFeastDinner requires formal serving style only
- Staff specialties affect preparation and delivery times

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- **Your Name** - *Initial work* - [YourGitHub](https://github.com/yourusername)

## 🙏 Acknowledgments

- Inspired by Domain-Driven Design principles
- Built for educational purposes demonstrating Java OOP concepts
- Showcases restaurant management system architecture

---

⭐ Star this repository if you found it helpful!
