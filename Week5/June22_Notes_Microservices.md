# Workflow
```
Monolithic Application
   ↓ Split by business domain
Multiple Microservices
   ↓ A single service cannot handle the traffic
Multiple Instances / Horizontal Scaling
   ↓ Instances dynamically join and leave
Service Discovery
   ↓ Select one instance from multiple healthy instances
Load Balancing
   ↓ Configuration should not be scattered across dozens of instances
Configuration Server + Secret Manager / Vault
   ↓ Determine whether the system is healthy
Monitoring, Logging, and Alerting
   ↓ The database can easily become a bottleneck
Local Cache + Redis
   ↓ Downstream services may fail
Timeout + Retry + Circuit Breaker + Fallback
   ↓ More services introduce higher networking and consistency costs
Make trade-offs based on business requirements instead of blindly adopting microservices
```



# Difference Between a Configuration Server and Vault

- Store regular configuration centrally in a Configuration Server.
- Separate configurations by environment, such as `dev / QA / UAT / prod`.
- Sensitive data such as passwords and database credentials should not be stored directly in the Configuration Server.
- Sensitive information should be stored in a Secret Manager such as Vault.



# Monitoring Is Not Just About Viewing Dashboards; It Should Form a Closed Alerting Loop

```
Spring Boot Actuator
        ↓
Prometheus collects metrics
        ↓
Grafana displays dashboards
        ↓
A threshold is reached
        ↓
Slack / Teams sends an alert
        ↓
Engineers inspect logs, reproduce the issue, and identify the root cause
```



# Two-Level Caching and the Cold Cache Problem

```
Request
   ↓
L1: Local Cache inside the application instance
   ↓ miss
L2: Centralized Redis
   ↓ miss
Database
```



# Temporary Persistence of Critical Requests

Temporarily persisting critical requests means reliably recording unfinished business requests first, so they are not lost because of a service outage, network failure, or process restart. The requests can then be completed through asynchronous processing, retries, and compensation mechanisms.



# Failure Handling

```
Service A calls Service B
Service B has already failed
        ↓
A large number of requests in Service A wait for a timeout
        ↓
Threads, connections, and memory are occupied
        ↓
Service A may also be overwhelmed
        ↓
Cascading failure
```

Then use:

```
A limited number of retries
       ↓ Still failing
Circuit Breaker opens
       ↓
Fail fast
       ↓
Fallback / Degraded response
```



Retries must always be limited.

- Use backoff, usually together with jitter.
- Not every exception should be retried.
- Write operations must consider idempotency.
- Excessive retries may create a retry storm and overwhelm a service that is still recovering.



# Microservices Are Not Suitable for Every Situation

Prime Video publicly shared a case study in 2023 about its **Video Quality Analysis (VQA) monitoring service**.

One of Prime Video's Video Quality Analysis services was responsible for detecting issues such as video freezing, corrupted frames, and audio-video synchronization problems in real time.

Initially, the system used AWS Step Functions, Lambda, and S3 to split media conversion, defect detection, and result aggregation into multiple distributed components.

Video frames first had to be uploaded to S3 and then downloaded by multiple detection services for processing. This generated a large amount of network traffic and many S3 API calls.

At the same time, every second of video triggered multiple Step Functions state transitions, causing costs and scaling pressure to increase rapidly.

The architecture encountered significant scalability and cost problems when it reached only about 5% of the target load.

Later, the team combined the Media Converter, Detectors, and Aggregation components into the same ECS Task and process, allowing video frames to be passed directly through memory.

The new design could still scale horizontally by running multiple ECS Tasks, while reducing unnecessary remote calls, intermediate storage, and orchestration overhead.

- In the Prime Video case, **one ECS Task could contain the Media Converter, multiple Detectors, and the Result Aggregator**. Because these components ran in the same Task or process, they could pass video frames directly through memory instead of using S3 and a large number of remote calls.
- ECS: AWS's container orchestration service. It is relatively simple and tightly integrated with AWS.
- EKS: AWS's managed Kubernetes service. It is more general-purpose but also more complex.

The final design reduced infrastructure costs by more than 90%. This case shows that tightly coupled components with high-frequency communication are not always suitable for being split into fine-grained microservices.
