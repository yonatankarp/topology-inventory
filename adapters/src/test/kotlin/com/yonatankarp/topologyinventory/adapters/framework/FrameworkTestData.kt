package com.yonatankarp.topologyinventory.adapters.framework

import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.EdgeRouter
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.Address
import com.yonatankarp.topologyinventory.domain.valueobject.CIDR
import com.yonatankarp.topologyinventory.domain.valueobject.City
import com.yonatankarp.topologyinventory.domain.valueobject.Country
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.Network
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkName
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType
import com.yonatankarp.topologyinventory.domain.valueobject.State
import com.yonatankarp.topologyinventory.domain.valueobject.SwitchType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor
import com.yonatankarp.topologyinventory.domain.valueobject.ZipCode
import com.yonatankarp.topologyinventory.domain.valueobject.latitude
import com.yonatankarp.topologyinventory.domain.valueobject.longitude

abstract class FrameworkTestData {
    protected var locationA =
        Location(
            address = Address("Amos Ln"),
            city = City("Tully"),
            state = State("NY"),
            country = Country("United States"),
            zipCode = ZipCode(13159u),
            latitude = 42.797310f.latitude(),
            longitude = (-76.130750f).longitude(),
        )

    protected var locationB =
        Location(
            address = Address("Av Republica Argentina 3109"),
            city = City("Curitiba"),
            state = State("PR"),
            country = Country("Italy"),
            zipCode = ZipCode(80610260u),
            latitude = 10f.latitude(),
            longitude = (-10f).longitude(),
        )

    protected var routers = mutableListOf<Router>()

    protected var switches = mutableListOf<Switch>()

    protected var networks = mutableListOf<Network>()

    protected var routersOfCoreRouter = mutableMapOf<Id, Router>()

    protected var switchesOfEdgeRouter = mutableMapOf<Id, Switch>()

    protected var network =
        Network(
            address = IP("20.0.0.0"),
            name = NetworkName("TestNetwork"),
            cidr = CIDR(8u),
        )

    protected var newNetwork =
        Network(
            address = IP("20.0.0.1"),
            name = NetworkName("NewNetwork"),
            cidr = CIDR(12u),
        )

    protected var networkSwitch =
        Switch(
            switchId = Id.withId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490"),
            routerId = Id.withId("e00ae038-aa7e-490c-a680-b1f183253e81"),
            vendor = Vendor.CISCO,
            model = Model.XYZ0004,
            ip = IP("20.0.0.100"),
            location = locationA,
            switchType = SwitchType.LAYER3,
            switchNetworks = networks,
        )

    protected var emptyNetworkSwitch =
        Switch(
            switchId = Id.withId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490"),
            routerId = Id.withId("e00ae038-aa7e-490c-a680-b1f183253e81"),
            vendor = Vendor.CISCO,
            model = Model.XYZ0004,
            ip = IP("20.0.0.101"),
            location = locationA,
            switchType = SwitchType.LAYER3,
        )

    protected var coreRouter =
        CoreRouter(
            id = Id.withoutId(),
            vendor = Vendor.HP,
            model = Model.XYZ0001,
            ip = IP("10.0.0.1"),
            location = locationA,
            routerType = RouterType.CORE,
            routers = routersOfCoreRouter,
        )

    protected var newCoreRouter =
        CoreRouter(
            id = Id.withId("81579b05-4b4e-4b9b-91f4-75a5a8561296"),
            vendor = Vendor.HP,
            model = Model.XYZ0001,
            ip = IP("10.1.0.1"),
            location = locationA,
            routerType = RouterType.CORE,
            routers = routersOfCoreRouter,
        )

    protected var edgeRouter =
        EdgeRouter(
            id = Id.withoutId(),
            vendor = Vendor.CISCO,
            model = Model.XYZ0002,
            ip = IP("20.0.0.1"),
            location = locationA,
            routerType = RouterType.EDGE,
            switches = switchesOfEdgeRouter,
        )

    protected var newEdgeRouter =
        EdgeRouter(
            id = Id.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003"),
            vendor = Vendor.CISCO,
            model = Model.XYZ0002,
            ip = IP("20.1.0.1"),
            location = locationA,
            routerType = RouterType.EDGE,
            switches = switchesOfEdgeRouter,
        )

    init {
        networks.add(network)
        switches.add(networkSwitch)
        switchesOfEdgeRouter[networkSwitch.id] = networkSwitch
        routersOfCoreRouter[edgeRouter.id] = edgeRouter
    }
}
