interface Player {
  id: number;
  name: string;
  coin: number;
}
async function fetchUsers(): Promise<Player[]> {
  const response = await fetch("/api/users");
  const data = await response.json();
  return data;
}
interface territory {
  ownerHashcode: number;
  deposit: number;
  max_deposit: number;
  x: number;
  y: number;
}
