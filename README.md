1. https://github.com/mbrodowski/koronaV2 - Order-Service - MS z zadania 1
1. https://github.com/mbrodowski/korona - Warehouse-Service - MS z zadania 3
1. https://github.com/mbrodowski/online-store-delivery - Courier-Way-Service MS z zadania 4
1. https://github.com/mbrodowski/order-delivery - Courier-Service - MS z zadania 5

# Odpowiedzialności
1. Order-Service - usługa pełni rolę mastera informacji o zamówienia
Udostępnia usługi:
	* zapisz zamówienie
	* udostępnij informacje o zamówieniu
	* przekaż zamówienie do realizacji
		1. Odpytanie odpytamy Warehouse-Service o magazyny, w których dostępne są produkty z zamówienia
	  1. Przekazanie zamówienia wraz z wyznaczonymi magazynami do Courier-Way-Service (każdy orderItem ma przypisany warehouseId)
		1. Courier-Way-Service wyznaczy trasę
		1. Trasę wyznaczoną przez Courier-Way-Service, należy zapisać w Delivery-Service zapisuje w bazie w postaci:
			  * Delivery odpowiada zamówieniu
			  * Delivery zawiera listę OrderItem, wraz z waypointNo (gdzie waypointNo numer wskazuje na kolejnośći obsługi danego OrderItem w ramach Delivery)
				* w odpowiedzi zwracane jest deliveryID
        * deliveryId zapisywane jest na zamówieniu
		1. DeliveryId powinno zostać przekazane do Courier-Service w celu przypisania przesyłki dla kuriera
1. Warehouse-Service zwróci listę magazynów dla pozycji zamówienia
  Udostępnia usługi:
    * Wyznacz trasę
      * Na wejściu lista OrderItem
      * Na wyjściu lista OrderItem, ze wskazanymi WarehouseId
    * Zwróć informacje o magazynie
      * Na wejściu warehouseID
      * Na wyjściu GeoLocation magazynu
1. Courier-Way-Service zwróci trasę dla wskazanego zamówią
   Udostępnia usługi:
     * Wyznacz trasę:
       * Na podstawie warehouseId powinno nastąpić odpytanie Warehouse-Service o GeoLocation magazynu
       * Do zaimplementowania jakiś prosty algorytm wyznaczający Długość Trasy (może ilość magazynów?)
       * Na wejściu lista OrderItem wraz z warehouseId 
       * Na wyjściu lista OrderItem, dla każdego itemu wyznaczony jest waypointNo oraz wyznaczona Długość Trasy     
1. Courier-Service dodanie nowej trasy dla kurier
  Udostępnia usługi:
    * przypisz przesyłkę (Delivery) do kuriera
      * na wejściu DeliveryId
      * na informacja o Delivery zostanie pobrana z Delivery-Service
    * zwróci kolejkę zadań dla kurier
1. Delivery-Service master informacji o Delivery
  * Udostępnia usługi:
    * Utwórz Delivery
      * na wejściu lista OrderItem wraz z warehouseId
      * na wyjściu DeliveryId
