# 🔎 Code Review Report

## 1. Issues in the Given Code

### ⚙️ Technical & Logic Issues
- **Unique SKU not enforced**  
  The code directly inserts a product without checking if `sku` already exists.  
  → SKUs must be unique across the platform.

- **Price field handling**  
  Price is stored as a **float**, which can cause precision errors.  
  → Use `Decimal` for financial calculations.

- **Products in multiple warehouses**  
  Current design assumes a product belongs to only one warehouse (`warehouse_id` in Product).  
  → Instead, products should exist independently, and **inventory should map product-to-warehouse**.

- **Field validation missing**  
  - No validation for required fields (`name`, `sku`, `price`).  
  - `initial_quantity` may be missing from request → `KeyError`.

- **Database transaction handling**  
  Two separate commits (`db.session.commit()`) are used.  
  → If inventory insert fails, product is still created → **inconsistent state**.

- **Optional fields not handled**  
  The code assumes all fields are always present in request JSON.

- **Error handling missing**  
  - No checks for SKU conflict.  
  - No proper error messages for DB issues.  
  → API may throw **500 errors** instead of clean responses.

---

## 2. ⚠️ Impacts in Production

- **Duplicate SKUs** → Inventory mismatches, reporting errors, product conflicts.  
- **Float imprecision in prices** → Wrong billing totals, financial discrepancies.  
- **Product tied to single warehouse** → Cannot scale if product exists in multiple warehouses.  
- **Crashes on missing fields** → API throws `500` instead of proper `400` response.  
- **Partial commits** → Orphan products without inventory → breaks reporting & analytics.  
- **Poor error handling** → Harder debugging, unreliable API for clients.

---
