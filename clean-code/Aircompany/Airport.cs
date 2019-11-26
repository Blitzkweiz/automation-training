using Aircompany.Models;
using Aircompany.Planes;
using System;
using System.Collections.Generic;
using System.Linq;

namespace Aircompany
{
    public class Airport
    {
        private readonly List<Plane> planes;

        public Airport(IEnumerable<Plane> planes)
        {
            this.planes = planes.ToList();
        }

        public List<PassengerPlane> GetPassengersPlanes()
        {
            return planes.Where(t => t.GetType() == typeof(PassengerPlane)).Cast<PassengerPlane>().ToList();
        }

        public List<MilitaryPlane> GetMilitaryPlanes()
        {
            return planes.Where(t => t.GetType() == typeof(MilitaryPlane)).Cast<MilitaryPlane>().ToList();
        }

        public PassengerPlane GetPassengerPlaneWithMaxPassengersCapacity()
        {
            return GetPassengersPlanes().Aggregate((w, x) => w.PassengersCapacity > x.PassengersCapacity ? w : x);             
        }

        public List<MilitaryPlane> GetTransportMilitaryPlanes()
        {
            return GetMilitaryPlanes().Where(plane => plane.MilitaryPlaneType == MilitaryType.TRANSPORT).ToList();
        }

        public Airport SortByMaxDistance()
        {
            return new Airport(planes.OrderBy(w => w.MaxFlightDistance));
        }

        public Airport SortByMaxSpeed()
        {
            return new Airport(planes.OrderBy(w => w.MaxSpeed));
        }

        public Airport SortByMaxLoadCapacity()
        {
            return new Airport(planes.OrderBy(w => w.MaxLoadCapacity));
        }


        public IEnumerable<Plane> GetPlanes()
        {
            return planes;
        }

        public override string ToString()
        {
            return "Airport{" +
                    "planes=" + string.Join(", ", planes.Select(x => x.Model)) +
                    '}';
        }
    }
}
