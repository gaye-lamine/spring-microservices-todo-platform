#!/bin/bash

# This script deploys the Todo platform to the production environment.

set -e

# Define variables
NAMESPACE="todo-platform"
HELM_RELEASE_NAME="todo-platform"
CHART_PATH="./charts/todo-platform-chart"

# Check if the namespace exists, if not create it
if ! kubectl get namespace $NAMESPACE; then
  kubectl create namespace $NAMESPACE
fi

# Deploy the application using Helm
helm upgrade --install $HELM_RELEASE_NAME $CHART_PATH --namespace $NAMESPACE --values $CHART_PATH/values.yaml

# Apply Kubernetes configurations
kubectl apply -f ./k8s/ingress.yaml -n $NAMESPACE
kubectl apply -f ./k8s/configmaps/common-config.yaml -n $NAMESPACE
kubectl apply -f ./k8s/secrets/prod-secrets.yaml -n $NAMESPACE

# Wait for the deployments to be ready
kubectl rollout status deployment/todo-service-deployment -n $NAMESPACE
kubectl rollout status deployment/user-service-deployment -n $NAMESPACE
kubectl rollout status deployment/api-gateway-deployment -n $NAMESPACE
kubectl rollout status deployment/notification-service-deployment -n $NAMESPACE

echo "Deployment to production completed successfully."