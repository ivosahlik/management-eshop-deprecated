!!!!!!! important !!!!!!!!
kubectl create secret tls membrania.eu.ssl-secret --key www.membrania.eu.key --cert www.membrania.eu.cer
->>>>>>> secret/membrania.eu.ssl-secret created
curl https://membrania.eu -kv

kubectl get secret membrania.eu.ssl-secret -o yaml

kubectl apply -f eshop-membrania-web-application-deployment.yaml,eshop-membrania-web-application-service.yaml
kubectl delete -f eshop-membrania-web-application-deployment.yaml,eshop-membrania-web-application-service.yaml

https://www.ssllabs.com/ssltest/
