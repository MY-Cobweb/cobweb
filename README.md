# COBWEB
一个支持混合存储，冷热分离，高效缓存的OLAP二次计算中间件，加速实时查询。

## 背景
真实场景下，我们的数据通常会存储到多种数据库的多张表中。例如Lambda架构下离线数据存储到ClickHouse离线表中，
实时数据存储到ClickHouse实时表中。如果对数据有更新操作，并且一致性要求高，实时数据还可能放到Mysql这种支持
事务的数据库中。这就导致开发人员需要合并来自多个数据库，多张表的数据，并进行二次聚合，才能得到最终的结果。
此外，开发人员还需要为实时数据，离线数据设置不同的缓存策略，在数据时效性和性能之间取得平衡。

为了让开发人员专注业务数据参出，减少数据合并，二次聚合，缓存设置等额外工作量，就有了COBWEB。COBWEB可以将不同
数据库中的多张表，映射为一张逻辑的主题表，开发人员只需访问这张逻辑表就能得到最终的数据。

## 特性
+ 支持Arrow列存格式规范
+ 冷热分离，预计算，可配置的缓存策略，极致的性能
+ 混合存储，当前支持MYSQL和ClickHouse(引入其他存储成本很小)，满足各种存储需求
+ 无共享架构，高可用，高可扩展
+ 简单，易用，提供最佳的用户体验

## 局限
+ 不能支持指标排序，因为比如点击率的排序需要实时数据的参与

## 架构

## LICENCE
Apache 2 LICENCE

## 贡献
COBWEB非常欢迎各种形式的贡献，包括issue，问题，文档，代码等。