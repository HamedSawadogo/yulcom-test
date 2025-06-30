# 📦 API de Gestion de Dossiers d'Import / Export

Cette API REST permet de créer et gérer des dossiers d'import/export contenant des produits et les fichiers réglementaires associés, selon des règles métier précises.  
Elle est conçue avec **Spring Boot** et expose une documentation interactive **Swagger**.

---

## 🚀 Documentation Swagger

📄 **Swagger UI** (interface de test et documentation)  
👉 [http://localhost:8092/api/v1/swagger-ui/index.html](http://localhost:8092/api/v1/swagger-ui/index.html)

---

## ✏️ Fonctionnalités principales

✅ Créer un dossier d’import ou export  
✅ Générer automatiquement les fichiers réglementaires selon :
- Type d'opération (IMPORT ou EXPORT)
- Montant total
- Nature des produits
- Devise utilisée

✅ Gérer les dépendances et priorités entre fichiers  
✅ Contrôler dynamiquement la visibilité et le cycle de vie des fichiers

---

## 📂 Endpoints principaux

### ➕ Créer un dossier (folder)

**URL :**

**Exemple de requête (JSON) :**
```json
{
  "type": "IMPORT",
  "products": [
    {
      "designation": "Acide Sulfurique",
      "nature": "CHEMICAL",
      "quantity": 1000,
      "unitValue": 15.50
    },
    {
      "designation": "Kit de test COVID-19",
      "nature": "PHARMACEUTICAL",
      "quantity": 500,
      "unitValue": 25.00
    },
    {
      "designation": "Riz Basmati 5kg",
      "nature": "FOOD_AND_BEVERAGE",
      "quantity": 200,
      "unitValue": 10.00
    },
    {
      "designation": "Smartphone X",
      "nature": "ELECTRONICS",
      "quantity": 50,
      "unitValue": 750.00
    }
  ],
  "currency": "EUR"
}
```
alement exposé pour obtenir un token d’authentification.

⚙️ Techniques et stack
Java 17

Spring Boot

Spring Security

Base de données en mémoire (PostgreSQL)

Swagger (OpenAPI)

JPA/Hibernate

📌 Pour démarrer le projet

# Lancer l'application
./mvnw spring-boot:run
Accéder ensuite à http://localhost:8092/api/v1/swagger-ui/index.html
