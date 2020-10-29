# Overview

Kubernetes is a software solution for Container Orchestration. We want to manage containers on a large scale, such as representing groups of containers and as many instances of a single container as we can, and track them. If they fail, we will want to quickly replace it. As well as quickly scale up or down the number of instances. We can also have support for common concepts such as API Gateway, Service Discovery, and Load Balancing.

## How it works

Clusters: A high level organizational unit for Kubernetes. Everything in Kubernetes is performed on a "cluster". A Cluster is a group of servers (each server would be referred to as a "node").
Generally, you might only have 1 cluster per global region.

One of our nodes will run the Kubernetes Control plane and act as the manager of all of the nodes. The rest will be worker nodes.

## Kubernetes Control Plane

The main process and it will act with all other processes.
This is our main entrypoint to our Cluster.

Kube-Controller Manager
  - This is the piece that is in charge of all controller objects
  - These controller objects are in charge of defining a piece of the "desired state"
Kube-Scheduler
  - Watch all objects and schedules the objects to nodes for them to execute
Etcd
  - This is a little database, a key-value store, to store everything that is needed for running the cluster

## Node Components

- Kubelet
  - Makes sure that the pods are running, keeps track of the state, and reports back to the control plane
- Kube-proxy
  - Manage networking for the node as well as redirection of requests to the appropriate pod
- Container Runtime
  - We do need Docker, or containerd, or some other runtime

Kube-dns (Service Discovery)
  - Generally on manager node, internal domain service for all of our Kubernetes objects

## Kubernetes Objects

- Pod
  - Is some number of containers from 1 to infinity
  - The containers are directly related to each other
  - Discrete piece of an application
  - Basic Pods will have just 1 container
  - Advanced Pods might have 2 containers
    - 1 for web service
    - 1 for containerized DB
  - Are ephemeral, they will be created and destroyed frequently
- Service
  - A controller object
  - An object that wraps all copies of the same pod behind 1 IP address, generally to make them more accessible
  - Static IP address
  - Redirect traffic to an available Pod (load balancing)
- Volume
  - Permanent data storage that can attach to one or more pods
- Namespace
  - Kind of like DB Schema, but for Kubernetes
  - Different objects in different namespaces don't really interact
  - Generally, it is to achieve multi-tenancy
- Deployment
  - One of the most common objects to create
  - A controller object
  - Describes how to create a set of pods
  - Rules for replicas
  - Will have a name
  - Rules to create each pod, and will create identical pods to reach # of replicas
- Ingress
  - Another controller object
  - Will act as the entrypoint for all services in our cluster
    - API Gateway
  - We don't necessarily have to expose our services to the outside internet, we only would have to expose the ingress point
  - Uses Nginx by default, which is also common for proxy servers

# How to create objects

We use Kubectl, which is a CLI for interacting with the kubernetes cluster
Most of the time, we'll be using `kubectl apply -f some_config_file.yml`

General Structure of the yml files:
- apiVersion
- Kind
- Metadata
- Spec

Types of Services:
- ClusterIP
  - This will make a service for a set of pods and give them one static ip address, but that address will only be accessible inside the cluster
- NodePort
  - Will do the same as above, but will redirect traffic from one port on the node itself to the clusterIP
  - Accessible to the outside world
  - Not recommended because it is hard to maintain and scale
- LoadBalancer
  - Generally uses cloud providers technology to build a load balancer
  - Generally supported through Clusters created also through those cloud providers

Can learn more at kubernetes.io/docs/home

For our demo, we will be using Minikube
  - A tool to build a local Kubernetes Cluster
  - It does require at least 2 cpus and 2 GB of RAM or so
    - We will need a t3.small or a t3a.small for AWS EC2s
